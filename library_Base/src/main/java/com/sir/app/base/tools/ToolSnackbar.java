package com.sir.app.base.tools;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Snackbar使用的时候需要CoordinatorLayout控件作为容器用来容纳Snackbar.
 * Created by zhuyinan on 2016/8/29.
 */
public class ToolSnackbar extends BaseTool {

    public static void showLong(@NonNull View view, String msg) {
        show(view, msg, Snackbar.LENGTH_LONG);
    }

    public static void showShort(@NonNull View view, String msg) {
        show(view, msg, Snackbar.LENGTH_SHORT);
    }

    public static void showLong(@NonNull View view, String msg, String text, OnClickListener listener) {
        show(view, msg, Snackbar.LENGTH_LONG, text, listener);
    }

    public static void showShort(@NonNull View view, String msg, String text, OnClickListener listener) {
        show(view, msg, Snackbar.LENGTH_SHORT, text, listener);
    }

    private static void show(@NonNull View view, String msg, int duration) {
        Snackbar.make(view, msg, duration).show();
    }

    private static void show(@NonNull View view, String msg, int duration, String text, OnClickListener listener) {
        Snackbar.make(view, msg, duration).setAction(text, listener).show();
    }
}
