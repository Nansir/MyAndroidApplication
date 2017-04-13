package com.sir.app.base;

import android.app.Activity;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sir.app.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * RecyclerAdapter基类
 * Created by zhuyinan on 2016/12/1.
 * Contact by 445181052@qq.com
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected Activity mContext;
    protected List<T> mDataList;
    protected SparseArrayCompat<View> mFootViews;
    protected SparseArrayCompat<View> mHeaderViews;
    protected OnItemClickListener listener;

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
                    return true;
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

    public abstract void onBindHolder(ViewHolder holder, int position);

    public abstract int bindLayout();

    /**
     * 填充数据,此方法会清空以前的数据
     *
     * @param list 需要显示的数据
     */
    public void addItem(Collection<? extends T> list) {
        mDataList.addAll(list);
    }

    /**
     * 更新数据
     *
     * @param position item对应的小标
     * @param data     item的数据
     */
    public void updateItem(int position, T data) {
        mDataList.set(position, data);
    }

    /**
     * 更新数据
     *
     * @param holder item对应的holder
     * @param data   item的数据
     */
    public void updateItem(ViewHolder holder, T data) {
        mDataList.set(holder.getLayoutPosition(), data);
    }

    /**
     * 获取一条数据
     *
     * @param holder item对应的holder
     * @return 该item对应的数据
     */
    public T getItem(ViewHolder holder) {
        return mDataList.get(holder.getLayoutPosition());
    }

    /**
     * 获取一条数据
     *
     * @param position item的位置
     * @return item对应的数据
     */
    public T getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * 追加一条数据
     *
     * @param data 追加的数据
     */
    public void addItem(T data) {
        mDataList.add(data);
    }

    /**
     * 追加一条数据
     *
     * @param data 追加的数据
     */
    public void addItem(T data, int position) {
        mDataList.add(position, data);
    }

    /**
     * 追加一个集合数据
     *
     * @param list 要追加的数据集合
     */
    public void appendList(Collection<? extends T> list) {
        mDataList.addAll(list);
    }


    /**
     * 删除数据Item
     *
     * @param position
     */
    public void removeItem(int position) {
        mDataList.remove(position);
    }


    /**
     * 移动Item
     *
     * @param fromPosition
     * @param toPosition
     */
    public void moveItem(int fromPosition, int toPosition) {
        //做数据的交换
        if (fromPosition < toPosition) {
            for (int index = fromPosition; index < toPosition; index++) {
                Collections.swap(mDataList, index, index + 1);
            }
        } else {
            for (int index = fromPosition; index > toPosition; index--) {
                Collections.swap(mDataList, index, index - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    private DisplayImageOptions mOption;

    public DisplayImageOptions getImageOptions() {
        return getImageOptions(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    public DisplayImageOptions getImageOptions(int onLoading, int onFail, int emptyUri) {
        if (mOption == null) {
            mOption = ((BaseApplication) BaseApplication.getContext()).getImageOptions(onLoading, onFail, emptyUri);
        }
        return mOption;
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
