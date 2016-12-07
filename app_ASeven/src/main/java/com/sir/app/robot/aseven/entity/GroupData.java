package com.sir.app.robot.aseven.entity;


import com.sir.app.base.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupData implements BaseExpandableListAdapter.BaseExpandableDataSource<ChildData> {

    private String name;

    private ArrayList<ChildData> items=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildData> getItems() {
        return items;
    }

    public void setItems(ArrayList<ChildData> items) {
        this.items = items;
    }

    @Override
    public List<ChildData> getChildList() {
        return items;
    }
}
