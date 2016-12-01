package com.sir.app.base;

/**
 * Adapter 接口
 * Created by zhuyinan on 2016/12/1.
 * Contact by 445181052@qq.com
 */

public interface IBaseAdapter {
    /**
     * 绑定渲染视图的布局文件
     * @return 布局文件资源id
     */
    public int bindLayout();

    public void onBindHolder(ViewHolder holder, int position);

}
