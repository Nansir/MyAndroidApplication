package com.sir.app.robot.aseven.adapter;

import android.app.Activity;
import android.view.View;

import com.sir.app.base.BaseAdapter;
import com.sir.app.base.ViewHolder;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.robot.aseven.R;
import com.sir.app.robot.aseven.entity.ChildData;

/**
 * Created by zhuyinan on 2016/12/6.
 * Contact by 445181052@qq.com
 */

public class ListViewAdapter extends BaseAdapter<ChildData> {

    public ListViewAdapter(Activity mContext) {
        super(mContext);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_child;
    }

    @Override
    public void onBindHolder(ViewHolder holder, final int position) {
        holder.setText(R.id.tv_item_child, getItem(position).getName());
        holder.setImageResource(R.id.iv_item_child, getItem(position).getImageId());
        holder.setOnClickListener(R.id.btn_item_child, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
