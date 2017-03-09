package com.sir.app.base;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 基础网络
 * Created by zhuyinan on 2016/12/22.
 * Contact by 445181052@qq.com
 */

public class BaseHttp {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.e("httpUrl：get", url);
        client.get(context, url, params, responseHandler);
    }

    public static void get(Context context, String url, AsyncHttpResponseHandler responseHandler) {
        Log.e("httpUrl：get", url);
        client.get(context, url, responseHandler);
    }


    public static void get(Context context, String url, BinaryHttpResponseHandler handler) {
        client.get(context,url,handler);
    }


    public static void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.e("httpUrl：post", url);
        client.post(context, url, params, responseHandler);
    }

    public static void cancel(Context context) {
        client.cancelRequests(context, true);
    }
}
