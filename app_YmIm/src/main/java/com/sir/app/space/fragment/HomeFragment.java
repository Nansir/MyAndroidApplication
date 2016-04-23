package com.sir.app.space.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;
import com.sir.app.base.BaseFragmentV4;
import com.sir.app.space.MainActivity;
import com.sir.app.space.R;

import java.util.ArrayList;


/**
 * 首页
 */
public class HomeFragment extends BaseFragmentV4 implements View.OnClickListener ,MainActivity.OnOptionTabListener{


    private ArrayList<Fragment> mOptionFragments = new ArrayList<>();
    public static HomeFragment getInstance() {
        return new HomeFragment();
    }



    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        LogUtils.e("initView 首页");
    }

    @Override
    public void doBusiness(Context mContext) {
        mOptionFragments.add(new InfoListFragment());
        mOptionFragments.add(new MapFragment());
        setShowFragment(0); //设置默认的Fragment
    }

    /**
     * 多个Fragment切换不重新实例化
     * @param position
     */
    private void setShowFragment(int position) {
        FragmentManager fm  = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (!mOptionFragments.get(position).isAdded()) {// 先判断是否被add过,
            transaction.add(R.id.fragment_home,mOptionFragments.get(position));
        }
        transaction.hide(mOptionFragments.get(position==0?1:0));
        transaction.show(mOptionFragments.get(position));
        transaction.commit();
    }


    @Override
    public void onClick(View v) {

    }



    @Override
    public void onOptionTabSelect(int position) {
        setShowFragment(position);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("HomeFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("HomeFragment onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("HomeFragment onDestroy");
    }
}