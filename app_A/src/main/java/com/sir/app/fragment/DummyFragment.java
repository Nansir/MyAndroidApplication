package com.sir.app.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sir.app.R;

/**
 * Created by zhuyinan on 2016/1/6.
 */

public class DummyFragment extends Fragment {

    int color;
    String conten;

    public DummyFragment() {
    }

    @SuppressLint("ValidFragment")
    public DummyFragment(String contnt, int color) {
        this.color = color;
        this.conten = contnt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text1);
        textView.setTextColor(color);
        textView.setText("ViewParent--" + conten);
        return view;
    }
}
