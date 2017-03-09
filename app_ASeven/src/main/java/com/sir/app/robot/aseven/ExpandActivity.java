package com.sir.app.robot.aseven;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.kyleduo.switchbutton.SwitchButton;
import com.sir.app.base.BaseActivity;
import com.sir.app.expand.ToggleExpandLayout;

import butterknife.Bind;

/**
 * 开关展开
 * Created by zhuyinan on 2017/1/2.
 * Contact by 445181052@qq.com
 */

public class ExpandActivity extends BaseActivity {


    @Bind(R.id.toogleLayout1)
    ToggleExpandLayout layout1;
    @Bind(R.id.toogleLayout2)
    ToggleExpandLayout layout2;
    @Bind(R.id.toogleLayout3)
    ToggleExpandLayout layout3;

    @Override
    public int bindLayout() {
        return R.layout.activity_expand;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

        SwitchButton switchButton = (SwitchButton) findViewById(R.id.switch_button);

        layout1.setOnToggleTouchListener(new ToggleExpandLayout.OnToggleTouchListener() {
            @Override
            public void onStartOpen(int height, int originalHeight) {
            }

            @Override
            public void onOpen() {
                int childCount = layout1.getChildCount();
                for(int i = 0; i < childCount; i++) {
                    View view = layout1.getChildAt(i);
                    view.setElevation(dp2px(1));
                }
            }

            @Override
            public void onStartClose(int height, int originalHeight) {
                int childCount = layout1.getChildCount();
                for(int i = 0; i < childCount; i++) {
                    View view = layout1.getChildAt(i);
                    view.setElevation(dp2px(i));
                }
            }

            @Override
            public void onClosed() {

            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layout1.open();
                } else {
                    layout1.close();
                }
            }
        });

        SwitchButton switchButton2 = (SwitchButton) findViewById(R.id.switch_button2);

        layout2.setOnToggleTouchListener(new ToggleExpandLayout.OnToggleTouchListener() {
            @Override
            public void onStartOpen(int height, int originalHeight) {
            }

            @Override
            public void onOpen() {
                int childCount = layout2.getChildCount();
                for(int i = 0; i < childCount; i++) {
                    View view = layout2.getChildAt(i);
                    view.setElevation(dp2px(1));
                }
            }

            @Override
            public void onStartClose(int height, int originalHeight) {
                int childCount = layout2.getChildCount();
                for(int i = 0; i < childCount; i++) {
                    View view = layout2.getChildAt(i);
                    view.setElevation(dp2px(i));
                }
            }

            @Override
            public void onClosed() {

            }
        });

        switchButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layout2.open();
                } else {
                    layout2.close();
                }
            }
        });


        SwitchButton switchButton3 = (SwitchButton) findViewById(R.id.switch_button3);

        layout3.setOnToggleTouchListener(new ToggleExpandLayout.OnToggleTouchListener() {
            @Override
            public void onStartOpen(int height, int originalHeight) {
            }

            @Override
            public void onOpen() {
                int childCount = layout3.getChildCount();
                for(int i = 0; i < childCount; i++) {
                    View view = layout3.getChildAt(i);
                    view.setElevation(dp2px(1));
                }
            }

            @Override
            public void onStartClose(int height, int originalHeight) {
                int childCount = layout3.getChildCount();
                for(int i = 0; i < childCount; i++) {
                    View view = layout3.getChildAt(i);
                    view.setElevation(dp2px(i));
                }
            }

            @Override
            public void onClosed() {

            }
        });

        switchButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.e("switchbutton open", "invoked");
                    layout3.open();
                } else {
                    Log.e("switchbutton close", "invoked");
                    layout3.close();
                }
            }
        });
    }

    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
