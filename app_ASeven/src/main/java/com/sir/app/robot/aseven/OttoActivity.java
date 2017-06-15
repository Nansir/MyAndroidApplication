package com.sir.app.robot.aseven;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.sir.app.base.BaseActivity;
import com.sir.app.base.event.BusProvider;
import com.sir.app.base.event.BusEvent;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import butterknife.OnClick;

/**
 * Created by zhuyinan on 2016/12/8.
 * Contact by 445181052@qq.com
 */

public class OttoActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_otto;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick(R.id.btn_Otto)
    public void onclick_otto(View view) {
        BusProvider.getBusInstance().post(new BusEvent("this is sendAnEvent"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("event", "onResume");
        BusProvider.getBusInstance().register(this);//注册
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("event", "onPause");
        BusProvider.getBusInstance().unregister(this);//注销
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Subscribe   //订阅事件DataChangedEvent
    public void sayGoodOnEvent(final BusEvent event) {
        Log.e("event", "good " + event.jsonStr);
    }

    @Produce    //产生事件
    public BusEvent produceDataChangedEvent() {
        //启动执行一次
        Log.e("event", "启动执行 produceDataChangedEvent");
        return new BusEvent("this is sendAnEvent");
    }

}
