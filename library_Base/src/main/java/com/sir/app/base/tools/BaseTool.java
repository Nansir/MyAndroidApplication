package com.sir.app.base.tools;

import android.content.Context;

import com.sir.app.base.common.BaseApplication;

/**
 * 工具基础类,所有Tool都继承ToolBase
 * @author zhuyinan
 * @date 2015-11-8
 */
public class BaseTool {
	/** 上下文 **/
	protected static final Context mContext = BaseApplication.getContext();
	
	public BaseTool(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}
}
