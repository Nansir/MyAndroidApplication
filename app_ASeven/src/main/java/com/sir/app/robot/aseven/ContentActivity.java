package com.sir.app.robot.aseven;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sir.app.base.BaseActivity;

/**
 * Created by zhuyinan on 2016/11/28.
 */

public class ContentActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_content;
    }

    @Override
    public void initView(View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = (TextView) findViewById(com.sir.app.base.R.id.toolbar_title);
        toolbarTitle.setText(getTitle());
    }

    @Override
    public void doBusiness(Context mContext) {

    }

}
