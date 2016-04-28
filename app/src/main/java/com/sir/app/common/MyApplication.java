package com.sir.app.common;

import com.sir.app.base.common.BaseApplication;
import com.sir.app.material.app.ThemeManager;

/**
 * Created by zhuyinan on 2016/4/27.
 */
public class MyApplication extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        //使用到app_Widget初始化主题信息
        ThemeManager.init(this, 2, 0, null);
    }
}
