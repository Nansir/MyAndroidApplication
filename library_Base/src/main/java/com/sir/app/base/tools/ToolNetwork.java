package com.sir.app.base.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

import com.orhanobut.logger.Logger;

import org.apache.http.conn.util.InetAddressUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 基于静态内部类实现的单例，保证线程安全的网络信息工具类 <per> 使用该工具类之前，记得在AndroidManifest.xml添加权限许可 <xmp>
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 * </xmp> </per>
 * 
 * 安卓判断网络状态，只需要在相应的Activity的相关方法（onCreat/onResum）调用一行代码即可
 * ToolNetwork.isAvailable.validateNetWork();
 * 
 */
@SuppressLint("DefaultLocale")
public class ToolNetwork extends BaseTool{

	public final static String TAG = ToolNetwork.class.getSimpleName();
	public final static String NETWORK_CMNET = "CMNET";
	public final static String NETWORK_CMWAP = "CMWAP";
	public final static String NETWORK_WIFI = "WIFI";
	private static NetworkInfo networkInfo = null;
	
	
	/**
	 * 判断网络是否可用
	 * 
	 * @return 是/否
	 */
	public static boolean isAvailable() {
		ConnectivityManager manager = (ConnectivityManager) mContext
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (null == manager) {
			return false;
		}
		networkInfo = manager.getActiveNetworkInfo();
		if (null == networkInfo || !networkInfo.isAvailable()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断网络是否已连接
	 * 
	 * @return 是/否
	 */
	public static boolean isConnected() {
		if (!isAvailable()) {
			return false;
		}
		if (!networkInfo.isConnected()) {
			return false;
		}
		return true;
	}

	/**
	 * 检查当前环境网络是否可用，不可用跳转至开启网络界面,不设置网络强制关闭当前Activity
	 */
	public static void validateNetWork() {
		if (!isConnected()) {
			Builder dialogBuilder = new AlertDialog.Builder(mContext);
			dialogBuilder.setTitle("网络设置");
			dialogBuilder.setMessage("网络不可用，是否现在设置网络？");
			dialogBuilder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							((Activity) mContext).startActivityForResult(
									new Intent(Settings.ACTION_SETTINGS),
									which);
						}
					});
			dialogBuilder.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			dialogBuilder.create();
			dialogBuilder.show();
		}else{
			ToolAlert.showShort("请检查网络");
		}
	}

	/**
	 * 获取网络连接信息</br> 无网络：</br> WIFI网络：WIFI</br> WAP网络：CMWAP</br>
	 * NET网络：CMNET</br>
	 * 
	 * @return
	 */
	public static String getNetworkType() {
		if (isConnected()) {
			int type = networkInfo.getType();
			if (ConnectivityManager.TYPE_MOBILE == type) {
				Log.i(TAG,
						"networkInfo.getExtraInfo()-->"
								+ networkInfo.getExtraInfo());
				if (NETWORK_CMWAP.equals(networkInfo.getExtraInfo()
						.toLowerCase())) {
					return NETWORK_CMWAP;
				} else {
					return NETWORK_CMNET;
				}
			} else if (ConnectivityManager.TYPE_WIFI == type) {
				return NETWORK_WIFI;
			}
		}

		return "";
	}

	/**
	 * 获取IP地址
	 * @return
     */
	public static String getLocalIpAddress() {
		try {
			Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces();
			// 遍历所用的网络接口
			while (en.hasMoreElements()) {
				NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
				Enumeration<InetAddress> inet = nif.getInetAddresses();
				// 遍历每一个接口绑定的所有ip
				while (inet.hasMoreElements()) {
					InetAddress ip = inet.nextElement();
					if (!ip.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(ip
							.getHostAddress())) {
						return ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			Logger.e(e, e.getMessage());
		}
		return null;
	}


}
