package com.sir.app.space.helper;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.sir.app.space.R;
import com.sir.app.space.entity.InfoEntity;

import java.util.ArrayList;

/**
 * Created by zhuyinan on 2016/3/29.
 */

public class MapMarkerHelper {

    private static int count = 1;
    private static Bitmap lastMarkerBitMap = null;

    static ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();

   static {
         // giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.point1));
        //giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.point2));
        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.point3));
        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.point4));
        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.point5));
        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.point6));
    }

    public static Marker showMarker(AMap aMap, InfoEntity info) {

        Marker marker = aMap.addMarker(new MarkerOptions()
                .title("1.多家专车被曝给司机报销罚款,滴滴被立案\n2.美国男子法庭飙歌求减刑,歌词里不断道歉")
                .position(info.latLng1).icons(giflist).period(8).zIndex(112));
        aMap.addMarker(new MarkerOptions()
                .title("强冷空气将席卷中东部,局部最高降温14度")
                .position(info.latLng2).icons(giflist).period(8).zIndex(113));
        aMap.addMarker(new MarkerOptions()
                .title("1.携女加油站自焚\n2.is包围俄特种兵")
                .position(info.latLng3).icons(giflist).period(8).zIndex(114));
        aMap.addMarker(new MarkerOptions()
                .title("笔帽上的这个小孔能救命,90%的人不知道")
                .position(info.latLng4).icons(giflist).period(8).zIndex(115));
        aMap.addMarker(new MarkerOptions()
                .title("1.打110辱骂接警员\n2.男子遭雷劈变女人")
                .position(info.latLng5).icons(giflist).period(8).zIndex(116));
        aMap.addMarker(new MarkerOptions()
                .title("笔帽上的这个小孔能救命,90%的人不知道").zIndex(117)
                .position(info.latLng6).icons(giflist).period(8));

        return marker;
    }


    public static void growInto(final Marker marker, final Handler handler) {
        marker.setVisible(false);
        final long start = SystemClock.uptimeMillis();
        final long duration = 250;// 动画总时长
        final Bitmap bitMap = marker.getIcons().get(0).getBitmap();// BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        final int width = bitMap.getWidth();
        final int height = bitMap.getHeight();

        final Interpolator interpolator = new AccelerateInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);

                if (t > 1) {
                    t = 1;
                }

                // 计算缩放比例
                int scaleWidth = (int) (t * width);
                int scaleHeight = (int) (t * height);
                if (scaleWidth > 0 && scaleHeight > 0) {

                    // 使用最原始的图片进行大小计算
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(Bitmap
                            .createScaledBitmap(bitMap, scaleWidth,
                                    scaleHeight, true)));
                    marker.setVisible(true);

                    // 因为替换了新的图片，所以把旧的图片销毁掉，注意在设置新的图片之后再销毁
                    if (lastMarkerBitMap != null
                            && !lastMarkerBitMap.isRecycled()) {
                        lastMarkerBitMap.recycle();
                    }

                    //第一次得到的缩放图片，在第二次回收，最后一次的缩放图片，在动画结束时回收
                    ArrayList<BitmapDescriptor> list = marker.getIcons();
                    if (list != null && list.size() > 0) {
                        // 保存旧的图片
                        lastMarkerBitMap = marker.getIcons().get(0).getBitmap();
                    }
                }

                if (t < 1.0 && count < 10) {
                    handler.postDelayed(this, 16);
                } else {
                    // 动画结束回收缩放图片，并还原最原始的图片
                    if (lastMarkerBitMap != null
                            && !lastMarkerBitMap.isRecycled()) {
                        lastMarkerBitMap.recycle();
                    }
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitMap));
                    marker.setVisible(true);
                }
            }
        });
    }
}
