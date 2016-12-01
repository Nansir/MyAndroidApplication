package com.sir.app.base.listener;

/**
 * 自定义点击监听
 * Created by zhuyinan on 2016/12/1.
 * Contact by 445181052@qq.com
 */

public interface OnItemClickListener<T> {
    void onItemClick(T t, int position);

    void onItemLongClick(T t, int position);
}
