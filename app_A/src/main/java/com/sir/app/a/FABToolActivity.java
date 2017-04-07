package com.sir.app.a;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.sir.app.R;
import com.sir.app.base.BaseActivity;
import com.sir.app.base.BaseAdapter;
import com.sir.app.base.ViewHolder;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.base.tools.ToolResource;
import com.sir.app.material.widget.ListViewExt;
import com.sir.app.a.widget.FABToolbarLayout;

/**
 * Created by zhuyinan on 2016/1/7.
 */

public class FABToolActivity extends BaseActivity implements View.OnClickListener {

    private FABToolbarLayout layout;
    private View one, two, three, four;
    private ListViewExt list;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(FABToolActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();


                    //设置组件的刷洗状态。
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 2:

                    break;
            }

        }
    };


    @Override
    public int bindLayout() {
        return R.layout.activity_fabtool;
    }

    @Override
    public void initView(View view) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout = (FABToolbarLayout) findViewById(R.id.fabtoolbar);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        list = (ListViewExt) findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.addItem("动感ListView");
        for (int i = 1; i < 30; i++) {
            myAdapter.addItem("Test" + i);
        }

        list.setAdapter(myAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                layout.show();
            }
        });
    }


    @Override
    public void doBusiness(Context mContext) {
        //设置进度圈的背景色。
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(ToolResource.getColor(mContext,R.color.colorPrimaryDark));
        //设置进度动画的颜色。
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        //设置进度圈的大小，只有两个值：DEFAULT、LARGE
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置手势滑动监听器。
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });

    }



    @Override
    public void onBackPressed() {
        layout.hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                ToolAlert.showShort(this,"拷贝");
                break;
            case R.id.two:
                ToolAlert.showShort(this,"粘贴");
                break;
            case R.id.three:
                ToolAlert.showShort(this,"全选");
                break;
            case R.id.four:
                ToolAlert.showShort(this,"剪切");
                break;
        }
        layout.hide();
    }


    class MyAdapter extends BaseAdapter {

        public MyAdapter(Activity mContext) {
            super(mContext);
        }

        @Override
        public int bindLayout() {
            return R.layout.dummy_fragment;
        }

        @Override
        public void onBindHolder(ViewHolder holder, int position) {
            holder.setText(R.id.text1,getItem(position).toString());
        }
    }
}
