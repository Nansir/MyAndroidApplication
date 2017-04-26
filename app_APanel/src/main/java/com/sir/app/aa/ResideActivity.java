package com.sir.app.aa;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sir.app.aa.widget.ResideLayout;
import com.sir.app.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by zhuyinan on 2017/4/26.
 */

public class ResideActivity extends BaseActivity {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

    @Bind(R.id.resideLayout)
    ResideLayout resideLayout;

    @Override
    public int bindLayout() {
        return R.layout.activity_reside;
    }

    @Override
    public void initView(View view) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText(getTitle());
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    public void toggleMenu(View view) {
        resideLayout.openPane();
    }
}
