package com.sir.app.base.tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sir.app.base.modle.NotificationMessage;

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

	/**
	 * 往状态栏发送一条通知消息
	 * 
	 * @param mContext
	 *            上下文
	 * @param message
	 *            消息Bean
	 */
	@SuppressWarnings("deprecation")
/*	public static void notification(Context mContext,
			NotificationMessage message) {

		// 消息管理器
		NotificationManager mNoticeManager = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// 构造Notification
		Notification notice = new Notification();
		notice.icon = message.getIconResId();
		notice.tickerText = message.getStatusBarText();
		notice.when = System.currentTimeMillis();
		*//**
		 * notification.defaults = Notification.DEFAULT_SOUND; // 调用系统自带声音
		 * notification.defaults = Notification.DEFAULT_VIBRATE;// 设置默认震动
		 * notification.defaults = Notification.DEFAULT_ALL; // 设置铃声震动
		 * notification.defaults = Notification.DEFAULT_ALL; // 把所有的属性设置成默认
		 *//*
		notice.defaults = Notification.DEFAULT_SOUND;// 通知时发出的默认声音
		*//**
		 * notification.flags = Notification.FLAG_NO_CLEAR; //
		 * 点击清除按钮时就会清除消息通知,但是点击通知栏的通知时不会消失 notification.flags =
		 * Notification.FLAG_ONGOING_EVENT; // 点击清除按钮不会清除消息通知,可以用来表示在正在运行
		 * notification.flags |= Notification.FLAG_AUTO_CANCEL; //
		 * 点击清除按钮或点击通知后会自动消失 notification.flags |= Notification.FLAG_INSISTENT;
		 * // 一直进行，比如音乐一直播放，知道用户响应
		 *//*
		notice.flags |= Notification.FLAG_AUTO_CANCEL; // 通知点击清除

		// 设置通知显示的参数
		Intent mIntent = new Intent(mContext, message.getForwardComponent());
		mIntent.setAction(ToolString.gainUUID());
		mIntent.putExtras(message.getExtras());
		mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		// 自动更新PendingIntent的Extra数据
		PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, mIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		notice.setLatestEventInfo(mContext, message.getMsgTitle(),
				message.getMsgContent(), pIntent);

		// 发送通知
		mNoticeManager.notify(message.getId(), notice);
	}*/

	/**
	 * 发送自定义布局通知消息
	 * 
	 * @param mContext
	 *            上下文
	 * @param message
	 *            消息Bean
	 */
	public static void notificationCustomView(Context mContext,
			NotificationMessage message) {

		// 消息管理器
		NotificationManager mNoticeManager = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// 构造Notification
		Notification mNotify = new Notification();
		mNotify.icon = message.getIconResId();
		mNotify.tickerText = message.getStatusBarText();
		mNotify.when = System.currentTimeMillis();
		mNotify.flags |= Notification.FLAG_AUTO_CANCEL; // 通知点击清除
		mNotify.contentView = message.getmRemoteViews();

		// 设置通知显示的参数
		Intent mIntent = new Intent(mContext, message.getForwardComponent());
		mIntent.setAction(ToolString.gainUUID());
		mIntent.putExtras(message.getExtras());
		mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
				mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mNotify.contentIntent = contentIntent;

		// 发送通知
		mNoticeManager.notify(message.getId(), mNotify);
	}
}
