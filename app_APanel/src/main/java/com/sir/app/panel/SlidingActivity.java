package com.sir.app.panel;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sir.app.panel.widget.SlidingLayout;
import com.sir.app.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by zhuyinan on 2017/4/26.
 */

public class SlidingActivity  extends BaseActivity{

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    SlidingLayout mMenu;
    @Override
    public int bindLayout() {
        return R.layout.activity_sliding;
    }

    @Override
    public void initView(View view) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText(getTitle());
        mMenu = (SlidingLayout) findViewById(R.id.id_menu);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    public void toggleMenu(View view) {
        mMenu.toggle();
    }
}
