package com.sir.app.base;

import android.content.Context;
import android.view.View;

/**
 * Activity接口
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public interface IBaseActivity {

	/**
	 * 绑定渲染视图的布局文件
	 * @return 布局文件资源id
	 */
	public int bindLayout();
	
	/**
	 * 初始化控件
	 */
	public void initView(final View view);
	
	/**
	 * 业务处理操作（onCreate方法中调用）
	 * @param mContext  当前Activity对象
	 */
	public void doBusiness(Context mContext);
	

}
