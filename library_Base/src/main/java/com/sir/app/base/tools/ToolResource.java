package com.sir.app.base.tools;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;

public class ToolResource extends BaseTool{
	/** 主线程ID */
	private static int mMainThreadId = -1;
	/** 主线程ID */
	private static Thread mMainThread;
	/** 主线程Handler */
	private static Handler mMainThreadHandler;
	/** 主线程Looper */
	private static Looper mMainLooper;

	public static Thread getMainThread() {
		if (mMainThread == null) {
			mMainThread = mContext.getMainLooper().getThread();
			mMainThreadId = (int) mMainThread.getId();
		}
		return mMainThread;
	}

	public static long getMainThreadId() {
		if (-1 == mMainThreadId) {
			getMainThread();
		}
		return mMainThreadId;
	}

	/** 获取主线程的handler */
	public static Handler getHandler() {
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

	/** 延时在主线程执行runnable */
	public static boolean postDelayed(Runnable runnable, long delayMillis) {
		return getHandler().postDelayed(runnable, delayMillis);
	}

	/** 在主线程执行runnable */
	public static boolean post(Runnable runnable) {
		return getHandler().post(runnable);
	}

	/** 从主线程looper里面移除runnable */
	public static void removeCallbacks(Runnable runnable) {
		getHandler().removeCallbacks(runnable);
	}

	public static View inflate(int resId) {
		return LayoutInflater.from(mContext).inflate(resId, null);
	}

	/** 获取资源 */
	public static Resources getResources() {
		return mContext.getResources();
	}

	/** 获取文字 */
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/** 获取文字数组 */
	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	/** 获取dimen */
	public static int getDimens(int resId) {
		return getResources().getDimensionPixelSize(resId);
	}

	/** 获取drawable */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/** 获取颜色 */
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/** 获取颜色选择器 */
	public static ColorStateList getColorStateList(int resId) {
		return getResources().getColorStateList(resId);
	}

	public static boolean isRunInMainThread() {
		return android.os.Process.myTid() == getMainThreadId();
	}

	public static void runInMainThread(Runnable runnable) {
		if (isRunInMainThread()) {
			runnable.run();
		} else {
			post(runnable);
		}
	}

	/**
	 * 强制忽略硬件菜单按钮，使actionbar上总是有三个点和overflow;
	 */
	public static void forceIgnorMenuDeviceButton() {
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
	public static void toggleInput() {
		InputMethodManager inputMethodManager = (InputMethodManager)mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.toggleSoftInput(0,
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 强制隐藏输入法键盘
	 */
	public static void hideInput(View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

}
