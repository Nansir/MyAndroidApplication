package com.sir.app.robot.aseven;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sir.app.base.BaseActivity;
import com.sir.app.robot.aseven.adapter.RecyclerViewAdapter;
import com.sir.app.robot.aseven.entity.ChildData;

import butterknife.Bind;

/**
 * Created by zhuyinan on 2016/12/6.
 * Contact by 445181052@qq.com
 */

public class RecyclerViewActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    RecyclerViewAdapter adapter;


    @Override
    public int bindLayout() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView(View view) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void doBusiness(Context mContext) {
        adapter = new RecyclerViewAdapter(this);
        for (int j = 0; j < 20; j++) {
            ChildData child = new ChildData();
            child.setName("RecyclerView" + (j + 1));
            child.setImageId(R.mipmap.ic_launcher);
            adapter.addItem(child);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        //ItemTouchHelper 用于实现 RecyclerView Item 拖曳效果的类
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //actionState : action状态类型，有三类 ACTION_STATE_DRAG （拖曳），ACTION_STATE_SWIPE（滑动），ACTION_STATE_IDLE（静止）
                int dragFlags = makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.UP | ItemTouchHelper.DOWN
                        | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//支持上下左右的拖曳
                int swipeFlags = makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//表示支持左右的滑动
                return makeMovementFlags(dragFlags, swipeFlags);//直接返回0表示不支持拖曳和滑动
            }

            /**
             * @param recyclerView attach的RecyclerView
             * @param viewHolder 拖动的Item
             * @param target 放置Item的目标位置
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//要拖曳的位置
                int toPosition = target.getAdapterPosition();//要放置的目标位置
                adapter.moveItem(fromPosition, toPosition);
                return true;
            }

            /**
             * @param viewHolder 滑动移除的Item
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();//获取要滑动删除的Item位置
                adapter.removeItem(position);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();//不支持长按拖曳效果直接返回false
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return super.isItemViewSwipeEnabled();//不支持滑动效果直接返回false
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.recyclerview_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.linear) {//线性布局
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerViewActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        } else if (itemId == R.id.grid) {//网格布局
            GridLayoutManager gridLayoutManager = new GridLayoutManager(RecyclerViewActivity.this, 3);
            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);

        } else if (itemId == R.id.staggered) {//瀑布流
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            recyclerView.setAdapter(adapter);

        } else if (itemId == R.id.insert_item) {//加一条
            ChildData child = new ChildData();
            child.setName("RecyclerView-add");
            child.setImageId(R.mipmap.ic_launcher);
            adapter.addItem(child, 3);
            adapter.notifyItemInserted(3);

        } else if (itemId == R.id.delete_item) {//减一条
            adapter.removeItem(3);
            adapter.notifyItemChanged(3);

        } else if (itemId == R.id.update_item) {//局部刷新
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();//获取第一个完全可见的Item的位置
                if (RecyclerView.NO_POSITION != position) {


                    adapter.notifyItemChanged(position);
                }

            }

        }
        return super.onOptionsItemSelected(item);
    }
}
