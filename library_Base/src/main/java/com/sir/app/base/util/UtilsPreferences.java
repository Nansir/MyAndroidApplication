package com.sir.app.base.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.sir.app.base.R;
import com.sir.app.base.common.BaseApplication;
import com.sir.app.base.tools.ToolResource;

/***
 * 轻量级的数据存储
 */
public class UtilsPreferences {

    public static String PREFERENCE_NAME = ToolResource.getString(R.string.app_name);
    public static Context mContext = BaseApplication.getContext();

    private UtilsPreferences() {
        throw new AssertionError();
    }

    public static boolean putString(String key, String value) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }


    public static String getString(String key) {
        return getString(key, null);
    }


    public static String getString(String key, String defaultValue) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }


    public static boolean putInt(String key, int value) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }


    public static int getInt(String key) {
        return getInt(key, -1);
    }


    public static int getInt(String key, int defaultValue) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }


    public static boolean putLong(String key, long value) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }


    public static long getLong(String key) {
        return getLong(key, -1);
    }


    public static long getLong(String key, long defaultValue) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }


    public static boolean putFloat(String key, float value) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }


    public static float getFloat(String key) {
        return getFloat(key, -1);
    }


    public static float getFloat(String key, float defaultValue) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }


    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }


    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }


    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }
}
