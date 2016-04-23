package com.sir.app.space.offlinemap;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.View;
import android.view.ViewGroup;

/**
 * ViewPager数据
 */
public class OfflinePagerAdapter extends PagerAdapter {

    private View mOfflineMapAllList;
    private View mOfflineDowloadedList;

    private ViewPager mContentViewPager;

    public OfflinePagerAdapter(ViewPager contentViewPager,View offlineMapAllList, View offlineDowloadedList) {
        mContentViewPager = contentViewPager;
        this.mOfflineMapAllList = offlineMapAllList;
        this.mOfflineDowloadedList = offlineDowloadedList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position == 0) {
            mContentViewPager.removeView(mOfflineMapAllList);
        } else {
            mContentViewPager.removeView(mOfflineDowloadedList);
        }
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        if (position == 0) {
            mContentViewPager.addView(mOfflineMapAllList);
            return mOfflineMapAllList;
        } else {
            mContentViewPager.addView(mOfflineDowloadedList);
            return mOfflineDowloadedList;
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
