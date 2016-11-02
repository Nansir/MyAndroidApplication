package com.space.app.base.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.space.app.base.common.MyApplication;

import java.util.Calendar;


/**
 * Created by zhuyinan on 2016/6/6.
 */
public abstract class BasePopupWindow {

    private int requseCode = 0;
    protected Context mContext = MyApplication.getContext();
    protected PopupWindow mPopupWindow;
    protected Calendar mCalendar;

    protected int popupWidth;
    protected int popupHeiget;

    public BasePopupWindow(Context context) {
        mContext = context;
    }

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

    protected void initPopupWindow() {
        mPopupWindow = new PopupWindow(getDataView());
        mPopupWindow.setWidth(getPopupWidth());
        mPopupWindow.setHeight(getPopupHeiget());
        mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        mPopupWindow.setTouchable(true); // 设置PopupWindow可触摸
        mPopupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        addListener(mPopupWindow);
    }

    protected void addListener(PopupWindow mPopupWindow) {

    }

    public int getPopupWidth() {
        return popupWidth == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : popupWidth;
    }

    public void setPopupWidth(int popupWidth) {
        this.popupWidth = popupWidth;
    }

    public int getPopupHeiget() {
        return popupHeiget == 0 ?ViewGroup.LayoutParams.WRAP_CONTENT : popupHeiget;
    }

    public void setPopupHeiget(int popupHeiget) {
        this.popupHeiget = popupHeiget;
    }



    protected abstract View getDataView();

    public int getRequseCode() {
        return requseCode;
    }

    public void setRequseCode(int requseCode) {
        this.requseCode = requseCode;
    }
}
