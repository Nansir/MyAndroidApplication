package com.space.app.base.tools;

import android.content.Context;

import com.space.app.base.base.BaseApplication;


/**
 * 工具基础类,所有Tool都继承ToolBase
 * Created by zhuyinan on 2016/8/29.
 */
public class BaseTool {
    /**
     * 上下文
     **/
    protected static final Context mContext = BaseApplication.getContext();

    public BaseTool() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
}
