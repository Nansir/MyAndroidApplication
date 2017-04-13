package com.sir.app.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import static android.R.attr.data;

/**
 * Adapter基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter implements IBaseAdapter<T> {
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

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(bindLayout(), null);
            holder = ViewHolder.get(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        onBindHolder(holder, position);
        return view;
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
     * 清除所有
     */
    @Override
    public void clearAll() {
        mDataList.clear();
    }

    /**
     * 添加数据
     */
    @Override
    public void addItem(T T) {
        mDataList.add(T);
    }

    /**
     * 在指定索引位置添加数据
     *
     * @param location 索引
     * @param data     数据
     */
    @Override
    public void addItem(int location, T data) {
        mDataList.add(location, data);
    }

    /**
     * 集合方式添加数据
     *
     * @param collection 集合
     */
    @Override
    public void addItem(Collection<? extends T> collection) {
        mDataList.addAll(collection);
    }

    /**
     * 移除指定索引位置对象
     *
     * @param location 删除对象索引位置
     * @return 被删除的对象
     */
    @Override
    public void removeItem(int location) {
        mDataList.remove(location);
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

    public abstract int bindLayout();

    public abstract void onBindHolder(ViewHolder holder, int position);

}
