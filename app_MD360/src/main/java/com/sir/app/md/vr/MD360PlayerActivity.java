package com.sir.app.md.vr;

import android.graphics.Bitmap;
import android.view.View;

import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.MDHotspotBuilder;
import com.asha.vrlib.model.MDPosition;
import com.asha.vrlib.plugins.MDAbsPlugin;
import com.asha.vrlib.plugins.MDHotspotPlugin;
import com.asha.vrlib.texture.MD360BitmapTexture;
import com.sir.app.base.BaseActivity;
import com.sir.app.md.R;


/**
 * vr播放基类
 * Created by zhuyinan on 2016/9/11
 * Contact by 445181052@qq.com
 */
public abstract class MD360PlayerActivity extends BaseActivity {

    private MDVRLibrary mVRLibrary;

    @Override
    public void initView(View view) {
        mVRLibrary = createVRLibrary();
    }

    abstract protected MDVRLibrary createVRLibrary();

    public MDVRLibrary getVRLibrary() {
        return mVRLibrary;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVRLibrary.onResume(this);
    }

    /**
     * 眼镜模式
     *
     * @param enabled
     */
    protected void setAntiDistortion(boolean enabled) {
        findViewById(R.id.tv_point2).setVisibility(enabled ? View.VISIBLE : View.GONE);
        mVRLibrary.switchDisplayMode(this, enabled ? MDVRLibrary.DISPLAY_MODE_GLASS : MDVRLibrary.DISPLAY_MODE_NORMAL);
        mVRLibrary.setAntiDistortionEnabled(enabled);
    }

    // MDVRLibrary.INTERACTIVE_MODE_MOTION
    // MDVRLibrary.INTERACTIVE_MODE_TOUCH
    // MDVRLibrary.INTERACTIVE_MODE_MOTION_WITH_TOUCH

    /**
     * 交互模式
     *
     * @param enabled
     */
    protected void setSwitchInteractiveMode(boolean enabled) {
        mVRLibrary.switchInteractiveMode(this, enabled ? MDVRLibrary.INTERACTIVE_MODE_TOUCH : MDVRLibrary.INTERACTIVE_MODE_MOTION);
    }

    /**
     * 失真模式
     *
     * @param enabled
     */
    private void setAtiDistortionEnabled(boolean enabled) {
        mVRLibrary.setAntiDistortionEnabled(enabled);
    }

    /**
     * 切换投影机
     * MDVRLibrary.PROJECTION_MODE_SPHERE
     * MDVRLibrary.PROJECTION_MODE_DOME180
     * MDVRLibrary.PROJECTION_MODE_DOME230
     * MDVRLibrary.PROJECTION_MODE_DOME180_UPPER
     * MDVRLibrary.PROJECTION_MODE_DOME230_UPPER
     * MDVRLibrary.PROJECTION_MODE_STEREO_SPHERE
     * MDVRLibrary.PROJECTION_MODE_PLANE_FIT
     * MDVRLibrary.PROJECTION_MODE_PLANE_CROP
     * MDVRLibrary.PROJECTION_MODE_PLANE_FULL
     * MDVRLibrary.PROJECTION_MODE_MULTI_FISH_EYE_HORIZONTAL
     * MDVRLibrary.PROJECTION_MODE_MULTI_FISH_EYE_VERTICAL
     * CustomProjectionFactory.CUSTOM_PROJECTION_FISH_EYE_RADIUS_VERTICAL
     */
    protected void setSwitchProjectionMode(int mode) {
        mVRLibrary.switchProjectionMode(MD360PlayerActivity.this, mode);
    }


    //Bitmap坐标
    private MDPosition position = MDPosition.newInstance().setY(-8.0f).setYaw(-90.0f);

    /**
     * 添加位图
     *
     * @param bitmap   图
     * @param listener 监听
     */
    protected MDHotspotPlugin addBitmap(final Bitmap bitmap, String title, MDVRLibrary.ITouchPickListener listener) {
        MDHotspotBuilder builder = MDHotspotBuilder.create()
                .size(5f, 5f)
                .provider(new MDVRLibrary.IBitmapProvider() {
                    @Override
                    public void onProvideBitmap(MD360BitmapTexture.Callback callback) {
                        callback.texture(bitmap);
                    }
                })
                .title(title)
                .position(position)
                .listenClick(listener);
        MDHotspotPlugin plugin = new MDHotspotPlugin(builder);
        getVRLibrary().addPlugin(plugin);
        return plugin;
    }

    /**
     * 移除位图
     *
     * @param plugin
     */
    protected void removeBitmap(MDAbsPlugin plugin) {
        getVRLibrary().removePlugin(plugin);
    }

    /**
     * 移除全部位图
     */
    protected void removeBitmapAll() {
        getVRLibrary().removePlugins();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVRLibrary.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVRLibrary.onDestroy();
    }
    /**
     * 隐藏progress
     */
    public void cancelBusy() {
        findViewById(R.id.progress).setVisibility(View.GONE);
    }
}