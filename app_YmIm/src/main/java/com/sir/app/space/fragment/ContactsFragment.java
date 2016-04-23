package com.sir.app.space.fragment;

import android.content.Context;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;
import com.sir.app.base.BaseFragmentV4;
import com.sir.app.space.R;

/**
 * 联系人
 */
public class ContactsFragment extends BaseFragmentV4 {


    public static ContactsFragment getInstance() {
        return new ContactsFragment();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_contacts;
    }

    @Override
    public void initView(View view) {
        LogUtils.e("initView 联系人");
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("ContactsFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("ContactsFragment onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("ContactsFragment onDestroy");
    }
}