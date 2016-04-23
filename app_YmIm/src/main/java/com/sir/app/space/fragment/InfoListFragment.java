package com.sir.app.space.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.lidroid.xutils.util.LogUtils;
import com.sir.app.base.BaseFragmentV4;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.base.tools.ToolResource;
import com.sir.app.material.widget.ListViewExt;
import com.sir.app.space.DetailsActivity;
import com.sir.app.space.R;
import com.sir.app.space.adapert.InfoListAdapter;
import com.sir.app.space.entity.InfoEntity;

import java.util.List;

/**
 * 信息列表
 */
public class InfoListFragment extends BaseFragmentV4 implements SwipeRefreshLayout.OnRefreshListener{


    private ListViewExt mListViewExt;
    private SwipeRefreshLayout mRefreshLayout;


    public static InfoListFragment getInstance() {
        return new InfoListFragment();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_infolist;
    }

    @Override
    public void initView(View view) {
        LogUtils.e("initView 信息列表");
        mListViewExt = (ListViewExt) view.findViewById(R.id.listview);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);


    }

    @Override
    public void doBusiness(Context mContext) {
        InfoListAdapter infolist=new InfoListAdapter();
        InfoEntity infoEntity = null;

        //模拟数据
        for (int i = 1; i < 20; i++) {
            infoEntity  = new InfoEntity();
            infolist.addItem(infoEntity);
        }
        mListViewExt.setAdapter(infolist);


        //设置进度圈的背景色。
        mRefreshLayout.setProgressBackgroundColorSchemeColor(ToolResource.getColor(R.color.colorPrimaryDark));
        //设置进度动画的颜色。
        mRefreshLayout.setColorSchemeResources(R.color.white);
        //设置进度圈的大小，只有两个值：DEFAULT、LARGE
        mRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置手势滑动监听器。
        mRefreshLayout.setOnRefreshListener(this);

        setListener();
    }

    private void setListener() {
        mListViewExt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getOperation().forward(DetailsActivity.class);
            }
        });
    }


    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ToolAlert.showShort("刷新完成");
                    mRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };




















    @Override
    public void onRefresh() {
        //刷新
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("InfoListFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("InfoListFragment onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("InfoListFragment onDestroy");
    }
}