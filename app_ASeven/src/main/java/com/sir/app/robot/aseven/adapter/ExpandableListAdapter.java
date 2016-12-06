package com.sir.app.robot.aseven.adapter;

import android.app.Activity;
import android.view.View;

import com.sir.app.base.BaseExpandableListAdapter;
import com.sir.app.base.ViewHolder;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.robot.aseven.R;
import com.sir.app.robot.aseven.entity.ChildData;
import com.sir.app.robot.aseven.entity.GroupData;

/**
 * Created by zhuyinan on 2016/12/6.
 * Contact by 445181052@qq.com
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter<ChildData, GroupData> {


    public ExpandableListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public int BingGroupView() {
        return R.layout.item_group;
    }

    @Override
    public int BingChildView() {
        return R.layout.item_child;
    }

    @Override
    public void updateGroupViewItem(ViewHolder viewHolder, int groupPosition, boolean b) {
        viewHolder.setText(R.id.tv_item_group, getGroup(groupPosition).getName());
    }

    @Override
    public void updateChildViewItem(ViewHolder viewHolder, final int groupPosition, final int childPosition, boolean b) {
        viewHolder.setText(R.id.tv_item_child, getChild(groupPosition, childPosition).getName());
        viewHolder.setImageResource(R.id.iv_item_child, getChild(groupPosition, childPosition).getImageId());
        viewHolder.setOnClickListener(R.id.btn_item_child, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToolAlert.showShort("点击"+groupPosition+"="+childPosition);

            }
        });
    }
}
