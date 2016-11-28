package com.sir.app.base;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment implements IBaseFragment {
    /**
     * 当前Fragment渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 共通操作
     **/
    private Operation mBaseOperation = null;

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
            mContextView = inflater.inflate(bindLayout(), container);
            // 控件初始化
            initView(mContextView);
            //实例化共通操作
            mBaseOperation = new Operation(getActivity());
        }

        //业务处理
        doBusiness(getActivity());
        return mContextView;
    }

    /**
     * 获取共通操作机能
     */
    protected Operation getOperation() {
        return this.mBaseOperation;
    }
}
