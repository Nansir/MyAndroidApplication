package com.sir.app.systemui.activity.translucent;

import android.os.Bundle;


import com.sir.app.systemui.R;
import com.sir.app.systemui.activity.base.BaseActivity;

/**
 * Translucent System Bar特性的使用
 *
 * @author Clock
 * @since 2016-02-03
 */
public class ImageTranslucentBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_translucent_bar);
    }
}
