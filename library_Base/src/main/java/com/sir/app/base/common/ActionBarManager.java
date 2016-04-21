package com.sir.app.base.common;

import android.support.v7.app.ActionBar;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sir.app.base.R;

/**
 * ActionBar管理器
 *
 * @author zhuyinan
 * @date 2015-10-5
 */
public class ActionBarManager {

    private static void initActionBar(Context mContext, ActionBar actionBar,
                                      CharSequence title) {
        if (actionBar == null) {
            return;
        }
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        TextView tvTitle = new TextView(mContext);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setTextColor(0xffffffff);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 21);
        actionBar.setCustomView(tvTitle, lp);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    /**
     * 设置居中标题
     *
     * @param mContext
     * @param actionBar
     */
    public static void initTitleCenterActionBar(Context mContext,
                                                ActionBar actionBar, CharSequence title) {
        initActionBar(mContext, actionBar, title);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

    }

    /***
     * 更新居中标题
     *
     * @param mContext
     * @param actionBar
     * @param title
     */
    public static void updateActionCenterTitle(Context mContext,
                                               ActionBar actionBar, CharSequence title) {
        ((TextView) actionBar.getCustomView().findViewById(R.id.title))
                .setText(title);
    }

    /**
     * 订制一个左菜单+居中标题的标题栏
     *
     * @param mContext
     * @param actionBar
     */
    public static void initMenuListTitle(Context mContext, ActionBar actionBar,
                                         CharSequence title) {
        initActionBar(mContext, actionBar, title);
        actionBar.setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标,对应ActionBar.DISPLAY_HOME_AS_UP
        actionBar.setDisplayShowTitleEnabled(false);// 对应ActionBar.DISPLAY_SHOW_TITLE
        actionBar.setHomeButtonEnabled(true);// 决定左上角的图标是否可以点击.4.0及其以上默认false
        //actionBar.setLogo(R.drawable.base_actionbar_list_white_48dp);// 设置添加左上角图标
        actionBar.setHomeAsUpIndicator(R.mipmap.base_actionbar_list_white_48dp);// 设置左上角图标
        actionBar.setDisplayUseLogoEnabled(true); // 设置左上角图标是否显示
        actionBar.setDisplayShowHomeEnabled(true);// 设置左上角图标是否显示

    }

    /**
     * 订制一个返回+居中标题的标题栏
     *
     * @param mContext
     * @param actionBar
     */
    public static void initBackTitle(Context mContext, ActionBar actionBar,
                                     CharSequence title) {
        initActionBar(mContext, actionBar, title);
        //actionBar.setTitle("返回"); // 设置左上角标题
        actionBar.setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标,对应ActionBar.DISPLAY_HOME_AS_UP
        actionBar.setDisplayShowTitleEnabled(false);// 对应ActionBar.DISPLAY_SHOW_TITLE
        actionBar.setDisplayShowHomeEnabled(true);// 使左上角图标是否显示
        actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);//替换默认返回图标
        actionBar.setHomeButtonEnabled(true);// 决定左上角的图标是否可以点击.4.0及其以上默认false
    }

    /**
     * 初始化【提交】右侧按钮菜单
     *
     * @param mOptionsMenu
     */
    public static void initActionBarSubmitButton(Menu mOptionsMenu) {
        final MenuItem searchItem = mOptionsMenu.findItem(R.id.action_search);
        if (null != searchItem) {
            searchItem.setVisible(false);
        }
        final MenuItem shareItem = mOptionsMenu.findItem(R.id.action_share);
        if (null != shareItem) {
            shareItem.setVisible(true);
        }
    }
}
