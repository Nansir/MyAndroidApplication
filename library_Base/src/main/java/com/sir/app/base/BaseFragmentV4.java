package com.sir.app.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Fragment基类(V4包)
 *  Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment {

    /**
     * 当前Fragment渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 共通操作
     **/
    private Operation mBaseOperation = null;

    private boolean hasFetchData; // 标识已经触发过懒加载数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //渲染视图View(防止切换时重绘View)
        if (null != mContextView) {
            ViewGroup parent = (ViewGroup) mContextView.getParent();
            if (null != parent) {
                parent.removeView(mContextView);
            }
        } else {
            mContextView = inflater.inflate(bindLayout(), null);
            //初始化ButterKnife
            ButterKnife.bind(this,mContextView);
            // 控件初始化
            initView(mContextView);
            //实例化共通操作
            mBaseOperation = new Operation(getActivity());
        }
        return mContextView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //业务处理操作
        doBusiness(getActivity());
        //视图已经准备完毕进行懒加载
        if (getUserVisibleHint() && !hasFetchData) {
            hasFetchData = true;
            lazyFetchData();
        }
    }

    /**
     * 获取共通操作机能
     */
    protected Operation getOperation() {
        return this.mBaseOperation;
    }

    public View findViewById(@IdRes int id) {
        return mContextView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);//解绑
    }

}
