package com.sir.app.a;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sir.app.R;
import com.sir.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyinan on 2016/1/11.
 * 详情可参考
 * http://blog.csdn.net/lmj623565791/article/details/45059587
 */
public class RecyclerViewActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<String> mDatas = new ArrayList<>();
    private HomeAdapter mAdapter;
    boolean flag;

    @Override
    public int bindLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public void initView(View view) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_recyclerview);
    }

    @Override
    public void doBusiness(Context mContext) {
        for (int i = 0; i < 10; i++) {
            mDatas.add("LinearLayoutManager");
            mDatas.add("现行管理器,支持横向、纵向");
            mDatas.add("GridLayoutManager");
            mDatas.add("网格布局管理器");
            mDatas.add("StaggeredGridLayoutManager");
            mDatas.add("瀑布就式布局管理器");
        }


        //设置布局管理器

        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));

        //设置adapter
        recyclerView.setAdapter(new HomeAdapter());
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {


        });

    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHolder> {
        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerViewActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false);
            myViewHolder my = new myViewHolder(view);
            return my;
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            holder.textView.setText(mDatas.get(position));
            flag = position % 2 == 0 ? true : false;
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class myViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;



            public myViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(android.R.id.text1);
                if (flag) {
                    textView.setBackgroundColor(getResources().getColor(R.color.wheat));
                } else {
                    textView.setBackgroundColor(getResources().getColor(R.color.salmon));
                }

            }
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }
}
