package com.space.app.base.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Fragment基类(V4包)
 * Created by zhuyinan on 2016/4/26.
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment {


    private View mContextView = null;

    private Operation mBaseOperation = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null != mContextView) {
            ViewGroup parent = (ViewGroup) mContextView.getParent();
            if (null != parent) {
                parent.removeView(mContextView);
            }
        } else {
            mContextView = inflater.inflate(bindLayout(), null);
            ButterKnife.bind(this,mContextView);

            initView(mContextView);
            mBaseOperation = new Operation(getActivity());
        }
        doBusiness(getActivity());
        return mContextView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);//解绑
    }

    protected Operation getOperation() {
        return this.mBaseOperation;
    }

}
