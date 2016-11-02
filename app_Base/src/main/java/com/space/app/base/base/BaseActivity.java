package com.space.app.base.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;
import com.space.app.base.common.MyApplication;
import com.space.app.base.config.SysKey;
import com.space.app.base.util.PreferenceShareUtil;

import java.lang.ref.WeakReference;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Activity基类
 * Created by zhuyinan on 2016/4/25.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {


    private MyApplication mApplication = null;

    private WeakReference<Activity> context = null;

    private Operation mBaseOperation = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLanguage();

        //设置渲染视图View
        setContentView(bindLayout());

        //初始化ButterKnife控件
        ButterKnife.bind(this);

        //获取应用Application
        mApplication = (MyApplication) getApplicationContext();

        //将当前Activity压入栈
        context = new WeakReference<Activity>(this);
        mApplication.pushTask(context);

        //实例化共通操作
        mBaseOperation = new Operation(this);


        //初始化控件
        initView(getWindow().getDecorView());

        //业务操作
        doBusiness(this);
    }


    private void setLanguage() {
        Resources resources = getResources();
        Configuration config =resources.getConfiguration();
        Locale locale = Locale.getDefault();
        String langStr = PreferenceShareUtil.getString(SysKey.CONFIG_LANGUAGE);
        //TODO 获取Sharedpreferences中存储的app语言环境
        if("zh".equals(langStr)){
            locale = Locale.CHINA;
            Logger.d("language ZH");
        }else if("en".equals(langStr)){
            Logger.d("language EN");
            locale =  Locale.ENGLISH;
        }
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication.removeTask(context);
    }

    /**
     * 获取当前Activity
     *
     * @return
     */
    protected Activity getContext() {
        if (null != context) {
            return context.get();
        } else {
            return null;
        }
    }

    /**
     * 获取共通操作机能
     */
    protected Operation getOperation() {
        return this.mBaseOperation;
    }

    /**
     * 整个应用Applicaiton
     */
    protected MyApplication getMyApplication() {
        return this.mApplication;
    }

    /**
     * Actionbar点击返回键关闭事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
