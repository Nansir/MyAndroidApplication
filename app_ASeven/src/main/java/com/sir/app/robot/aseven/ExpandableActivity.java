package com.sir.app.robot.aseven;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.sir.app.base.BaseActivity;
import com.sir.app.robot.aseven.adapter.ExpandableListAdapter;
import com.sir.app.robot.aseven.entity.ChildData;
import com.sir.app.robot.aseven.entity.GroupData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by zhuyinan on 2016/12/6.
 * Contact by 445181052@qq.com
 */

public class ExpandableActivity extends BaseActivity {

    @Bind(R.id.expanded_list)
    ExpandableListView listView;

    ExpandableListAdapter adapter;
    private List<GroupData> dataSource;

    @Override
    public int bindLayout() {
        return R.layout.activity_expandable;
    }

    @Override
    public void initView(View view) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void doBusiness(Context mContext) {
        initDataSource();
        adapter = new ExpandableListAdapter(this);
        adapter.addItem(dataSource);
        listView.setAdapter(adapter);

    }


    /**
     * 给数据源添加数据
     */
    private void initDataSource() {
        dataSource = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            GroupData group = new GroupData();
            group.setName("ExpandableListGroup" + (i + 1));
            ArrayList<ChildData> childs = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ChildData child = new ChildData();
                child.setName("ExpandableListChild" + (j + 1));
                child.setImageId(R.mipmap.ic_launcher);
                childs.add(child);
            }
            if (i == 2) {
                ChildData child = new ChildData();
                child.setName("ExpandableListChild" + 4);
                child.setImageId(R.mipmap.ic_launcher);
                childs.add(child);
            }
            group.setItems(childs);
            dataSource.add(group);
        }
    }
}
