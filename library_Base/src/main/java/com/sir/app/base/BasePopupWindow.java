package com.sir.app.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


/**
 * PopupWindow 基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BasePopupWindow {

    protected Context mContext = BaseApplication.getContext();
    protected PopupWindow mPopupWindow;
    protected int popupWidth;
    protected int popupHeiget;

    public void show(View belowView) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            if (mPopupWindow == null) {
                initPopupWindow();
            }
            mPopupWindow.showAsDropDown(belowView);
        }
    }

    private void initPopupWindow() {
        mPopupWindow = new PopupWindow(initView());
        mPopupWindow.setWidth(getPopupWidth());
        mPopupWindow.setHeight(getPopupHeiget());
        mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        mPopupWindow.setTouchable(true); // 设置PopupWindow可触摸
        mPopupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setPopupWindow(mPopupWindow);
    }

    protected void setPopupWindow(PopupWindow mPopupWindow) {

    }

    public int getPopupWidth() {
        return popupWidth == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : popupWidth;
    }

    public void setPopupWidth(int popupWidth) {
        this.popupWidth = popupWidth;
    }

    public int getPopupHeiget() {
        return popupHeiget == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : popupHeiget;
    }

    public void setPopupHeiget(int popupHeiget) {
        this.popupHeiget = popupHeiget;
    }

    protected abstract View initView();


}
