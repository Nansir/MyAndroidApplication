package com.space.app.base.tools;

import android.widget.Toast;


/**
 * 对话框工具类
 */
public class ToolAlert extends BaseTool{
	/**
	 * 弹出较短的Toast消息
	 * 
	 * @param msg
	 *            消息内容
	 */
	public static void showShort(String msg) {
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 弹出较长的Toast消息
	 * 
	 * @param msg
	 *            消息内容
	 */
	public static void showLong(String msg) {
		Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * 弹出自定义时长的Toast消息
	 * 
	 * @param msg
	 *            消息内容
	 */
	public static void show(String msg, int duration) {
		Toast.makeText(mContext, msg, duration).show();
	}




}
