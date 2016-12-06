package com.sir.app.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhuyinan on 2016/12/6.
 * Contact by 445181052@qq.com
 */

public abstract class BaseExpandableListAdapter<C, G extends BaseExpandableListAdapter.BaseExpandableDataSource<C>> extends android.widget.BaseExpandableListAdapter {

    /**
     * 数据存储集合
     **/
    protected List<G> mDataSource;
    /**
     * Context上下文
     **/
    protected Activity mContext;

    private BaseExpandableListAdapter() {
    }

    public BaseExpandableListAdapter(Activity activity) {
        this.mContext = activity;
        this.mDataSource = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return this.mDataSource.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mDataSource.get(groupPosition).getChildList() == null ? 0 : this.mDataSource.get(groupPosition).getChildList().size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return this.mDataSource.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return this.mDataSource.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(BingGroupView(), null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        updateGroupViewItem(viewHolder, groupPosition, b);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(BingChildView(), null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        updateChildViewItem(viewHolder, groupPosition, childPosition, b);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    /**
     * 集合方式添加数据
     *
     * @param collection 集合
     */
    public boolean addItem(Collection<? extends G> collection) {
        return mDataSource.addAll(collection);
    }

    /**
     * 添加数据
     */
    public boolean addItem(G object) {
        return mDataSource.add(object);
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDataSource.clear();
    }

    /***
     * ExpandableDataSource
     *
     * @param <T>
     */
    public interface BaseExpandableDataSource<T> {
        List<T> getChildList();
    }

    public abstract int BingGroupView();

    public abstract int BingChildView();

    public abstract void updateGroupViewItem(ViewHolder viewHolder, int groupPosition, boolean b);

    public abstract void updateChildViewItem(ViewHolder viewHolder, int groupPosition, int childPosition, boolean b);


}
