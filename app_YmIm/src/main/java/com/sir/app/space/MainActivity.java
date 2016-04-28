package com.sir.app.space;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sir.app.base.BaseActivity;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.space.entity.TabEntity;
import com.sir.app.space.fragment.ContactsFragment;
import com.sir.app.space.fragment.HomeFragment;
import com.sir.app.space.fragment.MeFragment;
import com.sir.app.space.fragment.MessageFragment;
import com.sir.app.space.helper.ToolbarHelper;
import com.sir.app.space.offlinemap.OfflineMapActivity;
import com.sir.app.tablayout.CommonTabLayout;
import com.sir.app.tablayout.SegmentTabLayout;
import com.sir.app.tablayout.listener.CustomTabEntity;
import com.sir.app.tablayout.listener.OnTabSelectListener;
import com.sir.app.tablayout.roundview.RoundTextView;
import com.sir.app.tablayout.utils.UnreadMsgUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "我"};
    private int[] mIconUnselectIds = {R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};

    private CommonTabLayout mTabLayout;
    private ViewPager mViewPager;



    private String[] mOptionTitles = {"列表", "地图"};
    private SegmentTabLayout mtlOption;
    private ActionBar myActionBar;




    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mViewPager = (ViewPager) findViewById(R.id.mviewpager);
        mtlOption = (SegmentTabLayout) findViewById(R.id.stl_homeOption);
        mTabLayout = (CommonTabLayout) findViewById(R.id.mtabyaout);
    }



    @Override
    public void doBusiness(Context mContext) {
        HomeFragment homeFragment = HomeFragment.getInstance();
        setOnOptionTabListener(homeFragment);

        mFragments.add(homeFragment);
        mFragments.add(MessageFragment.getInstance());
        mFragments.add(ContactsFragment.getInstance());
        mFragments.add(MeFragment.getInstance());


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }


        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setTabData(mTabEntities);
        setListener();
        setMsg();



        mtlOption.setTabData(mOptionTitles);
        mtlOption.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (tabListener!=null){
                    tabListener.onOptionTabSelect(position);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });


    }

    private void setListener() {
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout.showMsg(0, 12);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mtlOption.setVisibility(position == 0 ? View.VISIBLE : View.INVISIBLE);
                mTabLayout.setCurrentTab(position);
                myActionBar.setDisplayShowTitleEnabled(position != 0);
                ToolbarHelper.setTitle(myActionBar, mTitles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
    }


    /**
     * 设置消息提示
     */
    public void setMsg() {
        //两位数
        mTabLayout.showMsg(0, 55);
        mTabLayout.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout.showMsg(1, 100);
        mTabLayout.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTabLayout.showDot(2);
        RoundTextView rtv_2_2 = mTabLayout.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        //设置未读消息背景
        mTabLayout.showMsg(3, 5);
        mTabLayout.setMsgMargin(3, 0, 5);
        RoundTextView rtv_2_3 = mTabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.getDelegate().setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


    protected int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        //处理导航视图项
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                getOperation().forward(OfflineMapActivity.class);
                break;
            case R.id.nav_send:
                break;
        }
        return true;
    }


    /**
     * 监听[返回]键事件
     */
    private long waitTime = 2000;
    private long touchTime = 0;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                ToolAlert.showShort(getString(R.string.hint_out));
                touchTime = currentTime;
            } else {
                finish();
            }
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
        }
        return super.onOptionsItemSelected(item);
    }


    public OnOptionTabListener tabListener;

    public interface OnOptionTabListener {
        public void onOptionTabSelect(int position);
    }
    public  void setOnOptionTabListener(OnOptionTabListener onOptionTabListener){
        this.tabListener = onOptionTabListener;
    }

}
