package com.sir.app.a;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sir.app.R;
import com.sir.app.base.BaseActivity;
import com.sir.app.fragment.DummyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyinan on 2016/1/6.
 */
public class TabLayoutAcivity extends BaseActivity {
    @Override
    public int bindLayout() {
        return R.layout.activity_tablayout;
    }

    @Override
    public void initView(View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewPager viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DummyFragment("CAT",getResources().getColor(R.color.accent_material_light)), "CAT");
        adapter.addFrag(new DummyFragment("MOUSE",getResources().getColor(R.color.button_material_dark)), "MOUSE");
        adapter.addFrag(new DummyFragment("DOG",getResources().getColor(R.color.ripple_material_light)), "DOG");
        adapter.addFrag(new DummyFragment("MOUEY",getResources().getColor(R.color.button_material_dark)), "MOUEY");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
