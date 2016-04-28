package com.sir.app.a;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.sir.app.R;
import com.sir.app.base.BaseActivity;

/**
 * Created by zhuyinan on 2016/1/6.
 */

public class HideBarActivity extends BaseActivity {
    @Override
    public int bindLayout() {
        return R.layout.activity_hidebar;
    }

    @Override
    public void initView(View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }
}
