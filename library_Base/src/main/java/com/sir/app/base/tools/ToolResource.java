package com.sir.app.base.tools;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;

public class ToolResource extends BaseTool {
    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;

    public static Thread getMainThread(Context mContext) {
        if (mMainThread == null) {
            mMainThread = mContext.getMainLooper().getThread();
            mMainThreadId = (int) mMainThread.getId();
        }
        return mMainThread;
    }

    public static long getMainThreadId(Context mContext) {
        if (-1 == mMainThreadId) {
            getMainThread(mContext);
        }
        return mMainThreadId;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler(Context mContext) {
        /*
		 * //获得主线程的looper Looper mainLooper =
		 * BaseApplication.getMainThreadLooper(); //获取主线程的handler Handler
		 * handler = new Handler(mainLooper);
		 */
        if (mMainThreadHandler == null) {
            if (mMainLooper == null) {
                mMainLooper = mContext.getMainLooper();
            }
            mMainThreadHandler = new Handler(mMainLooper);
        }
        return mMainThreadHandler;
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Context mContext,Runnable runnable, long delayMillis) {
        return getHandler(mContext).postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Context mContext,Runnable runnable) {
        return getHandler(mContext).post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Context mContext,Runnable runnable) {
        getHandler(mContext).removeCallbacks(runnable);
    }

    public static View inflate(Context mContext,int resId) {
        return LayoutInflater.from(mContext).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources(Context mContext) {
        return mContext.getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(Context mContext,int resId) {
        return getResources(mContext).getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(Context mContext,int resId) {
        return getResources(mContext).getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(Context mContext,int resId) {
        return getResources(mContext).getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(Context mContext,int resId) {
        return getResources(mContext).getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(Context mContext,int resId) {
        return getResources(mContext).getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static ColorStateList getColorStateList(Context mContext,int resId, Resources.Theme theme) {
        return getResources(mContext).getColorStateList(resId, theme);
    }

    public static boolean isRunInMainThread(Context mContext) {
        return android.os.Process.myTid() == getMainThreadId(mContext);
    }

    public static void runInMainThread(Context mContext,Runnable runnable) {
        if (isRunInMainThread(mContext)) {
            runnable.run();
        } else {
            post(mContext,runnable);
        }
    }

    /**
     * 强制忽略硬件菜单按钮，使actionbar上总是有三个点和overflow;
     */
    public static void forceIgnorMenuDeviceButton(Context mContext) {
        try {
            ViewConfiguration config = ViewConfiguration.get(mContext);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");

            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            // presumably, not relevant
        }
    }

    /**
     * 切换软键盘的状态 如当前为收起变为弹出,若当前为弹出变为收起
     */
    public static void toggleInput(Context mContext) {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制隐藏输入法键盘
     */
    public static void hideInput(Context mContext,View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
