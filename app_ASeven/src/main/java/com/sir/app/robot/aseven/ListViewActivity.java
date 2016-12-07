package com.sir.app.robot.aseven;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.sir.app.base.BaseActivity;
import com.sir.app.robot.aseven.adapter.ListViewAdapter;
import com.sir.app.robot.aseven.entity.ChildData;

import butterknife.Bind;

/**
 * Created by zhuyinan on 2016/12/6.
 * Contact by 445181052@qq.com
 */

public class ListViewActivity extends BaseActivity {

    @Bind(R.id.listView)
    ListView listView;
    ListViewAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_listview;
    }

    @Override
    public void initView(View view) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void doBusiness(Context mContext) {
        adapter = new ListViewAdapter(this);
        for (int j = 0; j < 20; j++) {
            ChildData child = new ChildData();
            child.setName("ListView" + (j + 1));
            child.setImageId(R.mipmap.ic_launcher);
            adapter.addItem(child);
        }
        listView.setAdapter(adapter);
    }
}
