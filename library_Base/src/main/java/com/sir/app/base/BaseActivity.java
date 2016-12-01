package com.sir.app.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * android 系统中的四大组件之一Activity基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {

    /***
     * 整个应用Applicaiton
     **/
    private BaseApplication mApplication;
    /**
     * 当前Activity的弱引用，防止内存泄露
     **/
    private WeakReference<Activity> context;

    /**
     * 共通操作
     **/
    private Operation mBaseOperation;

    /**
     * 碎片集
     */
    private List<Fragment> mFragments;
    private int upPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置渲染视图View
        setContentView(bindLayout());

        //初始化ButterKnife控件
        ButterKnife.bind(this);

        //内存重启时调用
        if (savedInstanceState != null) {
            mFragments = getSupportFragmentManager().getFragments();
            upPosition = savedInstanceState.getInt("Fragment");
        }

        //获取应用Application
        mApplication = (BaseApplication) getApplicationContext();

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


    /**
     * 显示Fragment
     *
     * @param layoutID
     * @param position
     */
    protected void setShowFragment(int layoutID, int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!mFragments.get(position).isAdded()) {
            transaction.add(layoutID, mFragments.get(position));
            transaction.show(mFragments.get(position));
        } else {
            transaction.show(mFragments.get(position));
        }
        if (upPosition != position) {
            transaction.hide(mFragments.get(upPosition));
        }
        transaction.commit();
        upPosition = position;
    }


    //被系统杀死时候调用保存状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Fragment", upPosition);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);// 解绑
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
     * 设置透明主题
     * bindLayout里面调用
     */
    protected void setTranslucentTheme() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 设置语言
     */
    protected void setLanguage() {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        Locale locale = Locale.getDefault();
        String langStr = "";
        //TODO 获取Sharedpreferences中存储的app语言环境
        if ("zh".equals(langStr)) {
            locale = Locale.CHINA;
        } else if ("en".equals(langStr)) {
            locale = Locale.ENGLISH;
        }
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
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
    protected BaseApplication getMyApplication() {
        return mApplication;
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
