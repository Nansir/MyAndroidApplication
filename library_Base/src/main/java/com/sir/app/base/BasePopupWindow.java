package com.sir.app.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


/**
 * PopupWindow 基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BasePopupWindow extends PopupWindow {

    View mContextView;

    public BasePopupWindow(Context context) {
        super(context);

        mContextView = LayoutInflater.from(context).inflate(bindLayout(), null);

        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        setFocusable(true); // 设置PopupWindow可获得焦点
        setTouchable(true); // 设置PopupWindow可触摸
        setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(mContextView);

        onBindHolder(mContextView);

        setAnimationStyle(R.style.popup_window_anim_style);
    }

    public abstract int bindLayout();

    public abstract void onBindHolder(View view);

    public View findViewById(@IdRes int id) {
        return mContextView.findViewById(id);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 0-1
     */
    public void setBackgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

}
