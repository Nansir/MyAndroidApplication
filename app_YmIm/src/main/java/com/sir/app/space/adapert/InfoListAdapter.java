package com.sir.app.space.adapert;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.LogUtils;
import com.sir.app.base.BaseAdapter;
import com.sir.app.space.R;
import com.sir.app.space.common.MyApplication;
import com.sir.app.space.entity.InfoEntity;

/**
 * 显示信息列表
 * Created by zhuyinan on 2016/4/1.
 */

public class InfoListAdapter extends BaseAdapter {

    private Context mContext = MyApplication.getContext();
    private BitmapUtils bitmapUtils;
    private ImageAdapter imageAdapter;

    public InfoListAdapter() {
        bitmapUtils = new BitmapUtils(mContext);
        imageAdapter = new ImageAdapter(bitmapUtils);
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_launcher);//默认背景图片
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_launcher);//加载失败图片
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);//设置图片压缩类型
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InfoHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_message, null);
            holder = new InfoHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (InfoHolder) convertView.getTag();
        }
        setDataView(position, holder);

        return convertView;
    }

    private void setDataView(int position, InfoHolder holder) {
        InfoEntity infoEntity = (InfoEntity) getItem(position);
        bitmapUtils.display(holder.ivHead, infoEntity.head);
        LogUtils.d("头像：" + infoEntity.head);
        if (infoEntity.Images.length > 0) {
            holder.gvImages.setVisibility(View.VISIBLE);
            for (String url : infoEntity.Images) {
                imageAdapter.addItem(url);
                LogUtils.d("图片组："+url);
            }
            holder.gvImages.setAdapter(imageAdapter);
        }else{
            holder.gvImages.setVisibility(View.GONE);
        }

    }


    class InfoHolder {
        public TextView tvUser;
        public ImageView ivHead;
        public TextView tvContent;
        public GridView gvImages;

        public InfoHolder(View view) {
            tvUser = (TextView) view.findViewById(R.id.tv_user);
            ivHead = (ImageView) view.findViewById(R.id.iv_head);
            tvContent = (TextView) view.findViewById(R.id.tv_content);
            gvImages = (GridView) view.findViewById(R.id.gv_images);
        }
    }
}
