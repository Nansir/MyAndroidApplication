package com.sir.app.space.offlinemap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.amap.api.maps.offlinemap.OfflineMapStatus;
import com.sir.app.base.BaseActivity;
import com.sir.app.space.R;
import com.sir.app.tablayout.SegmentTabLayout;
import com.sir.app.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 离线地图下载
 */
public class OfflineMapActivity extends BaseActivity implements
        OfflineMapDownloadListener, OnClickListener {

    private OfflineMapManager amapManager = null;// 离线地图下载控制器
    private List<OfflineMapProvince> provinceList = new ArrayList<OfflineMapProvince>();// 保存一级目录的省直辖市

    // view pager 两个list以及他们的adapter
    private ViewPager mContentViewPage;
    private ExpandableListView mAllOfflineMapList;
    private ListView mDownLoadedList;

    private OfflineListAdapter adapter;
    private OfflineDownloadedAdapter mDownloadedAdapter;
    private PagerAdapter mPageAdapter;

    private MapView mapView;

    // 刚进入该页面时初始化弹出的dialog
    private ProgressDialog initDialog;

    /**
     * 更新所有列表
     */
    private final static int UPDATE_LIST = 0;
    /**
     * 显示toast log
     */
    private final static int SHOW_MSG = 1;

    private final static int DISMISS_INIT_DIALOG = 2;
    private final static int SHOW_INIT_DIALOG = 3;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_LIST:
                    if (mContentViewPage.getCurrentItem() == 0) {
                        ((BaseExpandableListAdapter) adapter)
                                .notifyDataSetChanged();
                    } else {
                        mDownloadedAdapter.notifyDataChange();
                    }

                    break;
                case SHOW_MSG:
                    break;

                case DISMISS_INIT_DIALOG:
                    initDialog.dismiss();
                    handler.sendEmptyMessage(UPDATE_LIST);
                    break;
                case SHOW_INIT_DIALOG:
                    if (initDialog != null) {
                        initDialog.show();
                    }

                    break;

                default:
                    break;
            }
        }

    };


    @Override
    public int bindLayout() {
        return R.layout.offline_map_layout;
    }


    private SegmentTabLayout stlmapOption;

    @Override
    public void initView(View view) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        stlmapOption = (SegmentTabLayout) findViewById(R.id.stl_mapOption);
        stlmapOption.setTabData(new String[]{"所有城市", "下载管理"});
        mContentViewPage = (ViewPager) findViewById(R.id.content_viewpage);
        // 此版本限制，使用离线地图，请初始化一个MapView
        mapView = new MapView(this);


        init();
    }

    @Override
    public void doBusiness(Context mContext) {

        // view pager 用到了所有城市list和已下载城市list所有放在最后初始化
        initAllCityList();
        initDownloadedList();
        mPageAdapter = new OfflinePagerAdapter(mContentViewPage, mAllOfflineMapList, mDownLoadedList);

        setListener();
    }


    /**
     * 设置监听
     */
    private void setListener() {
        mContentViewPage.setAdapter(mPageAdapter);
        mContentViewPage.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                stlmapOption.setCurrentTab(position);
                handler.sendEmptyMessage(UPDATE_LIST);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        stlmapOption.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mContentViewPage.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mContentViewPage.setCurrentItem(0);
    }


    /**
     * 初始化如果已下载的城市多的话，会比较耗时
     */
    private void initDialog() {

        initDialog = new ProgressDialog(this);
        initDialog.setMessage("正在获取离线城市列表");
        initDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        initDialog.setCancelable(false);
        initDialog.show();

        handler.sendEmptyMessage(SHOW_INIT_DIALOG);

        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();

                final Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do Work
                        init();
                        handler.sendEmptyMessage(DISMISS_INIT_DIALOG);
                        handler.removeCallbacks(this);
                        Looper.myLooper().quit();
                    }
                }, 10);
                Looper.loop();
            }
        }).start();
    }

    /**
     * 初始化UI布局文件
     */
    private void init() {


    }

    /**
     * 初始化所有城市列表
     */
    public void initAllCityList() {
        // 扩展列表
        View provinceContainer = LayoutInflater.from(OfflineMapActivity.this)
                .inflate(R.layout.offline_province_listview, null);
        mAllOfflineMapList = (ExpandableListView) provinceContainer .findViewById(R.id.province_download_list);

        amapManager = new OfflineMapManager(this, this);

        initProvinceListAndCityMap();

        adapter = new OfflineListAdapter(provinceList, amapManager,
                OfflineMapActivity.this);
        // 为列表绑定数据源
        mAllOfflineMapList.setAdapter(adapter);
        // adapter实现了扩展列表的展开与合并监听
        mAllOfflineMapList.setOnGroupCollapseListener(adapter);
        mAllOfflineMapList.setOnGroupExpandListener(adapter);
        mAllOfflineMapList.setGroupIndicator(null);
    }

    /**
     * sdk内部存放形式为<br>
     * 省份 - 各自子城市<br>
     * 北京-北京<br>
     * ...<br>
     * 澳门-澳门<br>
     * 概要图-概要图<br>
     * <br>
     * 修改一下存放结构:<br>
     * 概要图-概要图<br>
     * 直辖市-四个直辖市<br>
     * 港澳-澳门香港<br>
     * 省份-各自子城市<br>
     */
    private void initProvinceListAndCityMap() {

        List<OfflineMapProvince> lists = amapManager
                .getOfflineMapProvinceList();

        provinceList.add(null);
        provinceList.add(null);
        provinceList.add(null);
        // 添加3个null 以防后面添加出现 index out of bounds

        ArrayList<OfflineMapCity> cityList = new ArrayList<OfflineMapCity>();// 以市格式保存直辖市、港澳、全国概要图
        ArrayList<OfflineMapCity> gangaoList = new ArrayList<OfflineMapCity>();// 保存港澳城市
        ArrayList<OfflineMapCity> gaiyaotuList = new ArrayList<OfflineMapCity>();// 保存概要图

        for (int i = 0; i < lists.size(); i++) {
            OfflineMapProvince province = lists.get(i);
            if (province.getCityList().size() != 1) {
                // 普通省份
                provinceList.add(i + 3, province);
                // cityMap.put(i + 3, cities);
            } else {
                String name = province.getProvinceName();
                if (name.contains("香港")) {
                    gangaoList.addAll(province.getCityList());
                } else if (name.contains("澳门")) {
                    gangaoList.addAll(province.getCityList());
                } else if (name.contains("全国概要图")) {
                    gaiyaotuList.addAll(province.getCityList());
                } else {
                    // 直辖市
                    cityList.addAll(province.getCityList());
                }
            }
        }

        // 添加，概要图，直辖市，港口
        OfflineMapProvince gaiyaotu = new OfflineMapProvince();
        gaiyaotu.setProvinceName("概要图");
        gaiyaotu.setCityList(gaiyaotuList);
        provinceList.set(0, gaiyaotu);// 使用set替换掉刚开始的null

        OfflineMapProvince zhixiashi = new OfflineMapProvince();
        zhixiashi.setProvinceName("直辖市");
        zhixiashi.setCityList(cityList);
        provinceList.set(1, zhixiashi);

        OfflineMapProvince gaogao = new OfflineMapProvince();
        gaogao.setProvinceName("港澳");
        gaogao.setCityList(gangaoList);
        provinceList.set(2, gaogao);

        // cityMap.put(0, gaiyaotuList);// 在HashMap中第0位置添加全国概要图
        // cityMap.put(1, cityList);// 在HashMap中第1位置添加直辖市
        // cityMap.put(2, gangaoList);// 在HashMap中第2位置添加港澳

    }

    /**
     * 初始化已下载列表
     */
    public void initDownloadedList() {
        mDownLoadedList = (ListView) LayoutInflater.from(OfflineMapActivity.this).inflate(R.layout.offline_downloaded_list, null);
        android.widget.AbsListView.LayoutParams params = new android.widget.AbsListView.LayoutParams(android.widget.AbsListView.LayoutParams.MATCH_PARENT,
                android.widget.AbsListView.LayoutParams.WRAP_CONTENT);
        mDownLoadedList.setLayoutParams(params);
        mDownloadedAdapter = new OfflineDownloadedAdapter(this, amapManager);
        mDownLoadedList.setAdapter(mDownloadedAdapter);
    }

    /**
     * 暂停所有下载和等待
     */
    private void stopAll() {
        if (amapManager != null) {
            amapManager.stop();
        }
    }

    /**
     * 继续下载所有暂停中
     */
    private void startAllInPause() {
        if (amapManager == null) {
            return;
        }
        for (OfflineMapCity mapCity : amapManager.getDownloadingCityList()) {
            if (mapCity.getState() == OfflineMapStatus.PAUSE) {
                try {
                    amapManager.downloadByCityName(mapCity.getCity());
                } catch (AMapException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 取消所有<br>
     * 即：删除下载列表中除了已完成的所有<br>
     * 会在OfflineMapDownloadListener.onRemove接口中回调是否取消（删除）成功
     */
    private void cancelAll() {
        if (amapManager == null) {
            return;
        }
        for (OfflineMapCity mapCity : amapManager.getDownloadingCityList()) {
            if (mapCity.getState() == OfflineMapStatus.PAUSE) {
                amapManager.remove(mapCity.getCity());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
        }
        if (amapManager != null) {
            amapManager.destroy();
        }

        if (initDialog != null) {
            initDialog.dismiss();
            initDialog.cancel();
        }
    }

    private void logList() {
        ArrayList<OfflineMapCity> list = amapManager.getDownloadingCityList();

        for (OfflineMapCity offlineMapCity : list) {
            Log.i("amap-city-loading: ", offlineMapCity.getCity() + ","
                    + offlineMapCity.getState());
        }

        ArrayList<OfflineMapCity> list1 = amapManager
                .getDownloadOfflineMapCityList();

        for (OfflineMapCity offlineMapCity : list1) {
            Log.i("amap-city-loaded: ", offlineMapCity.getCity() + ","
                    + offlineMapCity.getState());
        }
    }

    /**
     * 离线地图下载回调方法
     */
    @Override
    public void onDownload(int status, int completeCode, String downName) {

        switch (status) {
            case OfflineMapStatus.SUCCESS:
                // changeOfflineMapTitle(OfflineMapStatus.SUCCESS, downName);
                break;
            case OfflineMapStatus.LOADING:
                Log.d("amap-download", "download: " + completeCode + "%" + ","
                        + downName);
                // changeOfflineMapTitle(OfflineMapStatus.LOADING, downName);
                break;
            case OfflineMapStatus.UNZIP:
                Log.d("amap-unzip", "unzip: " + completeCode + "%" + "," + downName);
                // changeOfflineMapTitle(OfflineMapStatus.UNZIP);
                // changeOfflineMapTitle(OfflineMapStatus.UNZIP, downName);
                break;
            case OfflineMapStatus.WAITING:
                Log.d("amap-waiting", "WAITING: " + completeCode + "%" + ","
                        + downName);
                break;
            case OfflineMapStatus.PAUSE:
                Log.d("amap-pause", "pause: " + completeCode + "%" + "," + downName);
                break;
            case OfflineMapStatus.STOP:
                break;
            case OfflineMapStatus.ERROR:
                Log.e("amap-download", "download: " + " ERROR " + downName);
                break;
            case OfflineMapStatus.EXCEPTION_AMAP:
                Log.e("amap-download", "download: " + " EXCEPTION_AMAP " + downName);
                break;
            case OfflineMapStatus.EXCEPTION_NETWORK_LOADING:
                Log.e("amap-download", "download: " + " EXCEPTION_NETWORK_LOADING "
                        + downName);
                Toast.makeText(OfflineMapActivity.this, "网络异常", Toast.LENGTH_SHORT)
                        .show();
                amapManager.pause();
                break;
            case OfflineMapStatus.EXCEPTION_SDCARD:
                Log.e("amap-download", "download: " + " EXCEPTION_SDCARD "
                        + downName);
                break;
            default:
                break;
        }

        // changeOfflineMapTitle(status, downName);
        handler.sendEmptyMessage(UPDATE_LIST);

    }

    @Override
    public void onCheckUpdate(boolean hasNew, String name) {
        // TODO Auto-generated method stub
        Log.i("amap-demo", "onCheckUpdate " + name + " : " + hasNew);
        Message message = new Message();
        message.what = SHOW_MSG;
        message.obj = "CheckUpdate " + name + " : " + hasNew;
        handler.sendMessage(message);
    }

    @Override
    public void onRemove(boolean success, String name, String describe) {
        // TODO Auto-generated method stub
        Log.i("amap-demo", "onRemove " + name + " : " + success + " , "
                + describe);
        handler.sendEmptyMessage(UPDATE_LIST);

        Message message = new Message();
        message.what = SHOW_MSG;
        message.obj = "onRemove " + name + " : " + success + " , " + describe;
        handler.sendMessage(message);

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }
}
