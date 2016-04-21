package com.sir.app;

import android.content.Context;
import android.view.View;

import com.sir.app.base.BaseFragmentV4;

/**
 * Created by zhuyinan on 2015/12/30.
 */

public class ProgressFragment extends BaseFragmentV4 {


    static ProgressFragment fragment;

    public static ProgressFragment newInstance() {
        if (fragment == null) {
            fragment = new ProgressFragment();
        }
        return fragment;
    }

    @Override
    public int bindLayout() {
        return R.layout.nav_header_main;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
