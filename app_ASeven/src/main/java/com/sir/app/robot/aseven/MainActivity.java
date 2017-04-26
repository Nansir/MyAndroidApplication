package com.sir.app.robot.aseven;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.sir.app.base.BaseActivity;
import com.sir.app.base.event.BusProvider;
import com.sir.app.base.event.DataChangedEvent;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.base.tools.ToolSnackbar;
import com.sir.app.robot.aseven.widget.WrapLayout;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.sb_horizontal)
    SeekBar sbHorizontal;
    @Bind(R.id.sb_vertical)
    SeekBar sbVertical;
    @Bind(R.id.rg_gravity)
    RadioGroup rgGravity;
    @Bind(R.id.wrapLayout)
    WrapLayout wrapLayout;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToolSnackbar.showLong(view, "Snackbar", "btn", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        rgGravity.setOnCheckedChangeListener(this);
        sbHorizontal.setOnSeekBarChangeListener(this);
        sbVertical.setOnSeekBarChangeListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe   //订阅事件DataChangedEvent
    public void sayGoodOnEvent(final DataChangedEvent event) {
        Log.e("event", "MainActivity " + event.getContent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("event", "onResume");
        BusProvider.getBusInstance().register(this);//注册
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @OnClick({R.id.btn_ExpandableList, R.id.btn_RecyclerView, R.id.btn_ListView
            , R.id.btn_Otto,R.id.btn_Expand,R.id.btn_FABTool,R.id.btn_ScreenSwitch})
    public void onclick_btn(View view) {
        switch (view.getId()) {
            case R.id.btn_ExpandableList:
                getOperation().forward(ExpandableActivity.class);
                break;
            case R.id.btn_RecyclerView:
                getOperation().forward(RecyclerViewActivity.class);
                break;
            case R.id.btn_ListView:
                getOperation().forward(ListViewActivity.class);
                break;
            case R.id.btn_Otto:
                getOperation().forward(OttoActivity.class);
                break;
            case R.id.btn_Expand:
                getOperation().forward(ExpandActivity.class);
                break;
            case R.id.btn_FABTool:
                getOperation().forward(FABToolActivity.class);
                break;
            case R.id.btn_ScreenSwitch:
                getOperation().forward(ScreenSwitchActivity.class);
                break;

        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id) {
            case R.id.rb_bottom:
                wrapLayout.setGravity(WrapLayout.GRAVITY_BOTTOM);
                break;
            case R.id.rb_center:
                wrapLayout.setGravity(WrapLayout.GRAVITY_CENTER);
                break;
            case R.id.rb_top:
                wrapLayout.setGravity(WrapLayout.GRAVITY_TOP);
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sb_horizontal:
                int horizontal = (int) (progress * getResources().getDisplayMetrics().density);
                wrapLayout.setHorizontalSpacing(horizontal);
                break;
            case R.id.sb_vertical:
                int vertical = (int) (progress * getResources().getDisplayMetrics().density);
                wrapLayout.setVerticalSpacing(vertical);
                break;
        }
    }
}
