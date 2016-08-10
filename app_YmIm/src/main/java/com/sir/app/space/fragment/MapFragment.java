package com.sir.app.space.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.lidroid.xutils.util.LogUtils;
import com.sir.app.base.BaseFragmentV4;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.space.R;
import com.sir.app.space.widget.FABToolbarLayout;

/**
 * 地图列表
 */
public class MapFragment extends BaseFragmentV4 implements View.OnClickListener{

    private AMap aMap;
    private MapView mMapView;
    private UiSettings mUiSettings;
    private View one, two, three, four;


    private FABToolbarLayout fabToolbarLayout;

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_map;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View view) {
        LogUtils.e("initView 地图列表");
        mMapView = (MapView) view.findViewById(R.id.map);
        if (aMap==null){
            aMap = mMapView.getMap();
            mUiSettings =aMap.getUiSettings();
        }

        fabToolbarLayout = (FABToolbarLayout) view.findViewById(R.id.fabtoolbar);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);



        setListener();
    }

    private void setListener() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        mUiSettings.setZoomControlsEnabled(false); //设置显示放大缩小按钮

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                ToolAlert.showShort("全部");
                break;
            case R.id.two:
                ToolAlert.showShort("公共");
                break;
            case R.id.three:
                ToolAlert.showShort("个人");
                break;
            case R.id.four:
                ToolAlert.showShort("换一批");
                break;
        }
        fabToolbarLayout.hide();
    }




    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("MapView onResume");
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("MapView onPause");
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("MapView onDestroy");
        mMapView.onDestroy();
    }

}