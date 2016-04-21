package com.sir.app;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sir.app.base.BaseActivity;
import com.sir.app.base.tools.ToolResource;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private SlidingMenu mMenu;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(ToolResource.getDrawable(R.mipmap.base_actionbar_list_white_48dp));
        setSupportActionBar(toolbar);
        mMenu = (SlidingMenu) findViewById(R.id.id_menu);
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    public void toggleMenu(View view) {
        mMenu.toggle();
    }

    @Override
    public void onClick(View view) {

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


    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }


}
