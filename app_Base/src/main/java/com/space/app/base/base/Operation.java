package com.space.app.base.base;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.DisplayMetrics;

import com.space.app.base.config.SysEnv;
import com.space.app.base.data.DTO;

import java.io.Serializable;

/**
 * 基本的操作共通抽取
 * Created by zhuyinan on 2016/4/25.
 */
public class Operation {

	/** 激活Activity组件意图 **/
	private Intent mIntent = new Intent();
	/*** 上下文 **/
	private Activity mContext = null;

	public Operation(Activity mContext) {
		this.mContext = mContext;
	}

	/**
	 * 正常Activity 不带返回
	 * 
	 * @param activity 需要跳转至的Activity
	 */
	public void forward(Class<? extends Activity> activity) {
		mIntent.setClass(mContext, activity);
		mContext.startActivity(mIntent);

	}


	/**
	 * 跳转Activity 带返回
	 *
	 * @param activity 需要跳转至的Activity
	 */
	public void forward(Class<? extends Activity> activity,int requestCode) {
		mIntent.setClass(mContext, activity);
		mContext.startActivityForResult(mIntent, requestCode);

	}

	/**
	 * 设置传递参数
	 * 
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(DTO<?, ?> value) {
		mIntent.putExtra(SysEnv.ACTIVITY_DTO_KEY, value);
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(String key, DTO<?, ?> value) {
		mIntent.putExtra(key, value);
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public Operation addParameter(String key, Bundle value) {
		mIntent.putExtra(key, value);
		return  this;
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public Operation addParameter(String key, Serializable value)
	{
		mIntent.putExtra(key, value);
		return  this;
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public Operation addParameter(String key, String value) {
		mIntent.putExtra(key, value);
		return this;
	}

	/**
	 * 设置传递参数
	 *
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public Operation addParameter(String key, int value) {
		mIntent.putExtra(key, value);
		return this;
	}


	/**
	 * 获取跳转时设置的参数
	 * 
	 * @param key
	 * @return
	 */
	public Object getParameters(String key) {
		DTO<String, Object> parms = getParameters();
		if (null != parms) {
			return parms.get(key);
		} else {
			parms = new DTO<String, Object>();
			parms.put(key, mContext.getIntent().getExtras().get(key));
		}
		return parms;
	}

	/**
	 * 获取跳转参数集合
	 * 
	 * @return
	 */
	public DTO<String, Object> getParameters() {
		@SuppressWarnings("unchecked")
		DTO<String, Object> parms = (DTO<String, Object>) mContext.getIntent()
				.getExtras().getSerializable(SysEnv.ACTIVITY_DTO_KEY);
		return parms;
	}

	/**
	 * 设置全局Application传递参数
	 * 
	 * @param strKey
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addGloableAttribute(String strKey, Object value) {
		BaseApplication.assignData(strKey, value);
	}

	/**
	 * 获取跳转时设置的参数
	 * 
	 * @param strKey
	 * @return
	 */
	public Object getGloableAttribute(String strKey) {
		return BaseApplication.gainData(strKey);
	}

	/** 启动一个服务 */
	public void launchService(Class<?> service) {
		Intent intent = new Intent(mContext, service);
		mContext.startService(intent);
	}

	/**
	 * 停止一个服务
	 */
	public void stopService(Class<?> service) {
		Intent intent = new Intent(mContext, service);
		mContext.stopService(intent);
	}

	/** 获取屏幕宽度 */
	public int getWidthPixels() {
		DisplayMetrics dm = new DisplayMetrics();
		mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/** 获取屏幕高度 */
	public int getHeightPixels() {
		DisplayMetrics dm = new DisplayMetrics();
		mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/** 通过Uri获取图片路径 */
	public String query(Uri uri) {
		Cursor cursor = mContext.getContentResolver().query(uri,
				new String[] { ImageColumns.DATA }, null, null, null);
		cursor.moveToNext();
		return cursor.getString(cursor.getColumnIndex(ImageColumns.DATA));
	}

}
