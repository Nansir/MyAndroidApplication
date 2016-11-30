package com.sir.app.a;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sir.app.R;
import com.sir.app.base.BaseActivity;
import com.sir.app.base.tools.ToolAlert;
import com.sir.app.base.tools.ToolSnackbar;
import com.sir.app.base.util.ScreenSwitchUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class ScreenSwitchActivity extends BaseActivity {

    private ScreenSwitchUtils instance;

    @Bind(R.id.switch_screen)
    Switch switchScreen;

    @Override
    public int bindLayout() {
        return R.layout.activity_screen_switch;
    }

    @Override
    public void initView(final View view) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instance = ScreenSwitchUtils.init(this.getApplicationContext());
        switchScreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ToolSnackbar.showLong(view, b ? "开启重力感应" : "关闭重力感应");
                if (b) {
                    instance.start(ScreenSwitchActivity.this);
                } else {
                    instance.stop();
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @Override
    protected void onStop() {
        super.onStop();
        instance.stop();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (instance.isPortrait()) {
            // 切换成竖屏
            ToolAlert.showShort("竖屏");
        } else {
            // 切换成横屏
           ToolAlert.showShort("横屏");
        }
    }


    @OnClick(R.id.button_screen)
    public void onClick_screen(View view) {
        if (switchScreen.isChecked()) {
            instance.toggleScreen();
        } else {
            ToolSnackbar.showLong(view, "开启重力感应");
        }
    }
}
