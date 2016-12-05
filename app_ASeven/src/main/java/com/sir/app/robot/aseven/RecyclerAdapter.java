package com.sir.app.robot.aseven;

import android.app.Activity;

import com.sir.app.base.BaseRecyclerAdapter;
import com.sir.app.base.ViewHolder;

/**
 * Created by zhuyinan on 2016/12/1.
 */

public class RecyclerAdapter extends BaseRecyclerAdapter {

    public RecyclerAdapter(Activity context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_recycler;
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        holder.setText(R.id.tv_item, (String) getItem(position));
    }
}
