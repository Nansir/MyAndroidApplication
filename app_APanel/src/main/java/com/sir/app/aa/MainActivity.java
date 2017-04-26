package com.sir.app.aa;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sir.app.base.BaseActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        findViewById(R.id.btn_reside).setOnClickListener(this);
        findViewById(R.id.btn_sliding).setOnClickListener(this);
        findViewById(R.id.btn_navigation).setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_reside) {
            getOperation().forward(ResideActivity.class);
        } else if (view.getId() == R.id.btn_sliding) {
            getOperation().forward(SlidingActivity.class);
        } else if (view.getId() == R.id.btn_navigation) {
            getOperation().forward(NavigationActivity.class);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
