package com.sir.app.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * android 系统中的四大组件之一Service基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BaseService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        doBusiness(intent, flags, startId);
        return super.onStartCommand(intent, flags, startId);
    }

    protected abstract void doBusiness(Intent intent, int flags, int startId);
}
