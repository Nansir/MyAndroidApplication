package com.sir.app.space.adapert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.sir.app.base.BaseAdapter;
import com.sir.app.space.common.MyApplication;

/**
 * Created by zhuyinan on 2016/4/5.
 */

public class ImageAdapter extends BaseAdapter {

    public Context mContext = MyApplication.getContext();
    private BitmapUtils bitmapUtils;

    public  ImageAdapter( BitmapUtils bitmap){
        bitmapUtils = bitmap;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }
        bitmapUtils.display(imageView, (String) getItem(position));
        return imageView;
    }
}
