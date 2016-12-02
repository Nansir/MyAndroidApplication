package com.sir.app.web.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.sir.app.web.handler.AndServerPingHandler;
import com.sir.app.web.handler.AndServerTestHandler;
import com.sir.app.web.handler.AndServerUploadHandler;
import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.AndServerBuild;

/**
 *
 * Created by zhuyinan on 2016/6/28.
 */
public class MyAndServer extends Service {


    private AndServer mAndServer;
    public static final int PORT = 6868;// 指定端口号

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("创建服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("开始服务");
        startAndServer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("服务销毁");
        if (mAndServer != null && mAndServer.isRunning()) {
            mAndServer.close();
        }
    }

    private void startAndServer() {
        if (mAndServer == null || !mAndServer.isRunning()) {

            AndServerBuild andServerBuild = AndServerBuild.create();
            andServerBuild.setPort(PORT);

            //添加普通接口
            andServerBuild.add("ping", new AndServerPingHandler());// 到时候在浏览器访问是：http://localhost:4477/ping
            andServerBuild.add("test", new AndServerTestHandler());// 到时候在浏览器访问是：http://localhost:4477/test

            //添加接受客户端上传文件的接口
            andServerBuild.add("upload", new AndServerUploadHandler());// 到时候在浏览器访问是：http://localhost:4477/upload
            mAndServer = andServerBuild.build();

            //启动服务器
            mAndServer.launch();
        }
    }
}
