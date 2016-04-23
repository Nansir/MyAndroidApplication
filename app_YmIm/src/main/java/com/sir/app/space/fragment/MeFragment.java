package com.sir.app.space.fragment;

import android.content.Context;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;
import com.sir.app.base.BaseFragmentV4;
import com.sir.app.space.R;

/**
 * 我
 */
public class MeFragment extends BaseFragmentV4 {

    public static MeFragment getInstance(){
        return new MeFragment();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(View view) {
        LogUtils.e("initView 我");

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("MeFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("MeFragment onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("MeFragment onDestroy");
    }
}