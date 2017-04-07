package com.sir.app.base.tools;

import android.content.Context;
import android.util.TypedValue;

/***
 * 常用单位转换的辅助类
 *Created by zhuyinan on 2016/12/8.
 */
public class ToolDensity extends BaseTool {
    /**
     * dp转px
     *
     * @param dpVal
     * @return
     */
    public static int dp2px(Context mContext,float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, mContext.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int sp2px(Context mContext,float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, mContext.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public static float px2dp(Context mContext,float pxVal) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }
}
