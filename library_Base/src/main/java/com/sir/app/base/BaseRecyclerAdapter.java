package com.sir.app.base;

import android.app.Activity;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sir.app.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * RecyclerAdapter基类
 * Created by zhuyinan on 2016/12/1.
 * Contact by 445181052@qq.com
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> implements IBaseAdapter {

    protected Activity mContext;
    protected List<Object> mDataList;
    protected SparseArrayCompat<View> mFootViews;
    protected SparseArrayCompat<View> mHeaderViews;
    protected OnItemClickListener<ViewHolder> listener;

    private final int BASE_ITEM_TYPE_HEADER = 100000;
    private final int BASE_ITEM_TYPE_FOOTER = 200000;


    private BaseRecyclerAdapter() {

    }

    public BaseRecyclerAdapter(Activity context) {
        this.mContext = context;
        this.mDataList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        onBindHolder(holder, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(holder, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (listener != null) {
                    listener.onItemLongClick(holder, position);
                }
                return false;
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (mHeaderViews.get(viewType) != null) {
//            return ViewHolder.get(mHeaderViews.get(viewType));
//        } else if (mFootViews.get(viewType) != null) {
//            return ViewHolder.get(mFootViews.get(viewType));
//        }
        return ViewHolder.get(mContext, bindLayout(), parent);
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (isHeaderViewPos(position)) {
//            return mHeaderViews.keyAt(position);
//        } else if (isFooterViewPos(position)) {
//            return mFootViews.keyAt(position - getHeadersCount() - getItemCount());
//        }
//        return super.getItemViewType(position);
//    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getItemCount();
    }

    public int getHeadersCount() {
        return mHeaderViews == null ? 0 : mHeaderViews.size();
    }


    public int getFootersCount() {
        return mFootViews == null ? 0 : mFootViews.size();
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();// + getItemCount() + getFootersCount();
    }

    /**
     * 填充数据,此方法会清空以前的数据
     *
     * @param list 需要显示的数据
     */
    public void addItem(Collection<? extends Object> list) {
        mDataList.addAll(list);
    }

    /**
     * 更新数据
     *
     * @param position item对应的小标
     * @param data     item的数据
     */
    public void updateItem(int position, Object data) {
        mDataList.set(position, data);
    }

    /**
     * 更新数据
     *
     * @param holder item对应的holder
     * @param data   item的数据
     */
    public void updateItem(ViewHolder holder, Object data) {
        mDataList.set(holder.getLayoutPosition(), data);
    }

    /**
     * 获取一条数据
     *
     * @param holder item对应的holder
     * @return 该item对应的数据
     */
    public Object getItem(ViewHolder holder) {
        return mDataList.get(holder.getLayoutPosition());
    }

    /**
     * 获取一条数据
     *
     * @param position item的位置
     * @return item对应的数据
     */
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * 追加一条数据
     *
     * @param data 追加的数据
     */
    public void addItem(Object data) {
        mDataList.add(data);
    }

    /**
     * 追加一个集合数据
     *
     * @param list 要追加的数据集合
     */
    public void appendList(Collection<? extends Object> list) {
        mDataList.addAll(list);
    }

    /**
     * 在最顶部前置数据
     *
     * @param data 要前置的数据
     */
    public void preposeItem(Object data) {
        mDataList.add(0, data);
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDataList.clear();
    }


    /**
     * 设置点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    /**
     * 添加标题视图
     *
     * @param view
     */
    public void addHeaderView(View view) {
        if (mHeaderViews == null) {
            mHeaderViews = new SparseArrayCompat<>();
        }
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * 添加脚视图
     *
     * @param view
     */
    public void addFootView(View view) {
        if (mFootViews == null) {
            mFootViews = new SparseArrayCompat<>();
        }
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }
}
