package com.space.app.custom.fragment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sir.app.base.BaseFragmentV4;
import com.sir.app.tablayout.CommonTabLayout;
import com.sir.app.tablayout.SlidingTabLayout;
import com.sir.app.tablayout.listener.CustomTabEntity;
import com.sir.app.tablayout.listener.OnTabSelectListener;
import com.space.app.custom.R;
import com.space.app.custom.entity.TabEntity;

import java.util.ArrayList;

/**
 * Created by zhuyinan on 2015/12/31.
 */

public class FabricFragment extends BaseFragmentV4 {

    private MyAdapter adapter;
    private RecyclerView mRecyclerView;
    private String[] mTitles = {"全部", "化纤", "混纺", "全毛"};

    private int mData[];

    private int[] mFeed = {R.color.aqua, R.color.mediumspringgreen, R.color.bisque, R.color.burlywood,
            R.color.whitesmoke, R.color.blue_btn_bg_color, R.color.trans_success_stroke_color, R.color.tomato, R.color.teal,
            R.color.skyblue, R.color.violet, R.color.aqua, R.color.mediumspringgreen, R.color.bisque, R.color.burlywood,
            R.color.whitesmoke, R.color.blue_btn_bg_color, R.color.paleturquoise, R.color.seagreen};
    private int[] mFeed1 = {R.color.aqua, R.color.mediumspringgreen, R.color.bisque, R.color.burlywood};
    private int[] mFeed2 = {R.color.whitesmoke, R.color.blue_btn_bg_color, R.color.trans_success_stroke_color, R.color.tomato, R.color.teal,
            R.color.skyblue, R.color.violet, R.color.aqua};
    private int[] mFeed3 = {R.color.mediumspringgreen, R.color.bisque, R.color.burlywood,
            R.color.whitesmoke, R.color.blue_btn_bg_color, R.color.paleturquoise, R.color.seagreen};


    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.fragment_fabric;
    }

    @Override
    public void initView(View view) {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }

        CommonTabLayout mCommonTabLayout = (CommonTabLayout) view.findViewById(R.id.ctl_son);
        mCommonTabLayout.setTabData(mTabEntities);
        mCommonTabLayout.setCurrentTab(0);
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        mData = mFeed;
                        break;
                    case 1:
                        mData = mFeed1;
                        break;
                    case 2:
                        mData = mFeed2;
                        break;
                    case 3:
                        mData = mFeed3;
                        break;
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
        mData = mFeed;
        adapter = new MyAdapter();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_fabric, parent, false);
            return new myViewHolder(view);
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            holder.imageView.setBackgroundColor(getResources().getColor(mData[position]));
        }

        @Override
        public int getItemCount() {
            return mData.length;
        }

        class myViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public myViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.iv_feed);
            }
        }
    }
}
