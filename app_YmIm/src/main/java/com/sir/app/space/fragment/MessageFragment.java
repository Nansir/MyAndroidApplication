package com.sir.app.space.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;
import com.sir.app.base.BaseFragmentV4;
import com.sir.app.space.R;


/**
 * 消息
 */
public class MessageFragment extends BaseFragmentV4 {

    public static MessageFragment getInstance() {
        return new MessageFragment();
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView(View view) {
        LogUtils.e("initView 消息");

    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("MessageFragment onCreate");
    }




    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("MessageFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("MessageFragment onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("MessageFragment onDestroy");
    }
}