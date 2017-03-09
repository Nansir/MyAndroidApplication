package com.sir.app.base.listener;

import com.sir.app.base.ViewHolder;

/**
 * 自定义点击监听
 * Created by zhuyinan on 2016/12/1.
 * Contact by 445181052@qq.com
 */

public interface OnItemClickListener {
    void onItemClick(ViewHolder holder, int position);

    void onItemLongClick(ViewHolder holder, int position);
}
