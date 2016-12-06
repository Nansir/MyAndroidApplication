package com.sir.app.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import static android.R.attr.data;

/**
 * Adapter基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    /**
     * 数据存储集合
     **/
    private List<T> mDataList;
    /**
     * Context上下文
     **/
    private Activity mContext;
    /**
     * 每一页显示条数
     **/
    private int mPerPageSize = 10;

    private BaseAdapter() {
        throw new UnsupportedOperationException("Adapter context is null");
    }

    public BaseAdapter(Activity mContext) {
        this(mContext, 10);
    }

    public BaseAdapter(Activity mContext, int mPerPageSize) {
        this.mContext = mContext;
        this.mPerPageSize = mPerPageSize;
        this.mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        if (position < mDataList.size()) {
            return mDataList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取当前页
     *
     * @return 当前页
     */
    public int getPageNo() {
        return (getCount() / mPerPageSize) + 1;
    }

    /**
     * 添加数据
     */
    public boolean addItem(T T) {
        return mDataList.add(T);
    }

    /**
     * 在指定索引位置添加数据
     *
     * @param location 索引
     * @param data     数据
     */
    public void addItem(int location, T data) {
        mDataList.add(location, data);
    }

    /**
     * 集合方式添加数据
     *
     * @param collection 集合
     */
    public boolean addItem(Collection<? extends T> collection) {
        return mDataList.addAll(collection);
    }

    /**
     * 在指定索引位置添加数据集合
     *
     * @param location   索引
     * @param collection 数据集合
     */
    public boolean addItem(int location, Collection<? extends T> collection) {
        return mDataList.addAll(location, collection);
    }

    /**
     * 移除指定对象数据
     *
     * @param T 移除对象
     * @return 是否移除成功
     */
    public boolean removeItem(T T) {
        return mDataList.remove(T);
    }

    /**
     * 移除指定索引位置对象
     *
     * @param location 删除对象索引位置
     * @return 被删除的对象
     */
    public T removeItem(int location) {
        return mDataList.remove(location);
    }

    /**
     * 移除指定集合对象
     *
     * @param collection 待移除的集合
     * @return 是否移除成功
     */
    public boolean removeAll(Collection<? extends T> collection) {
        return mDataList.removeAll(collection);
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDataList.clear();
    }

    /**
     * 获取Activity方法
     *
     * @return Activity的子类
     */
    public Activity getActivity() {
        if (null != mContext) {
            return mContext;
        }
        return null;
    }

    private DisplayImageOptions mOption;

    public DisplayImageOptions getImageOptionsn() {
        return getImageOptionsn(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    public DisplayImageOptions getImageOptionsn(int onLoading, int onFail, int emptyUri) {
        if (mOption == null) {
            mOption = ((BaseApplication) BaseApplication.getContext()).getImageOptions(onLoading, onFail, emptyUri);
        }
        return mOption;
    }

    /**
     * ViewHolder的内存优化方案
     *
     * @param view
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getViewHolder(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    /**
     * 更新Item视图，减少不必要的重绘
     *
     * @param listView
     */
    public void updateItemView(ListView listView, int position) {
        //换算成 Item View 在 ViewGroup 中的 index
        int index = position - listView.getFirstVisiblePosition();
        if (index >= 0 && index < listView.getChildCount()) {
            //更新单个Item
            View itemView = listView.getChildAt(index);
            getView(position, itemView, listView);
        }
    }

}
