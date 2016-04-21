package com.sir.app.fragment;

import android.content.Context;
import android.view.View;

import com.sir.app.R;
import com.sir.app.base.BaseFragmentV4;
import com.sir.app.material.widget.FloatingActionButton;

/**
 * Widget Button
 * Created by zhuyinan on 2015/12/31.
 */
public class ButtonFragment extends BaseFragmentV4 implements View.OnClickListener {

    @Override
    public int bindLayout() {
        return R.layout.fragment_button;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.button_bt_float).setOnClickListener(this);
        view.findViewById(R.id.button_bt_float_color).setOnClickListener(this);
        view.findViewById(R.id.button_bt_float_wave).setOnClickListener(this);
        view.findViewById(R.id.button_bt_float_wave_color).setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onClick(View view) {
        if (view instanceof FloatingActionButton) {
            FloatingActionButton bt = (FloatingActionButton) view;
            bt.setLineMorphingState((bt.getLineMorphingState() + 1) % 2, true);
        }
    }
}
