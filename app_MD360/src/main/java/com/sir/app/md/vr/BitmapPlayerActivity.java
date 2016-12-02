package com.sir.app.md.vr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.MDRay;
import com.asha.vrlib.plugins.IMDHotspot;
import com.asha.vrlib.texture.MD360BitmapTexture;
import com.sir.app.md.R;
import com.sir.app.md.common.SysKey;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import static com.squareup.picasso.MemoryPolicy.NO_CACHE;
import static com.squareup.picasso.MemoryPolicy.NO_STORE;

/**
 * 图片MD360Player
 * Created by zhuyinan on 2016/9/11
 * Contact by 445181052@qq.com
 */
public abstract class BitmapPlayerActivity extends MD360PlayerActivity {

    private Target mTarget;

    @Override
    public void doBusiness(Context mContext) {
        cancelBusy();
    }

    private void loadImage(Uri uri, final MD360BitmapTexture.Callback callback) {
        mTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                //如果通知大小的变化
                getVRLibrary().onTextureResize(bitmap.getWidth(), bitmap.getHeight());
                // texture
                callback.texture(bitmap);
                cancelBusy();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(getApplicationContext()).load(uri).resize(3072, 2048).centerInside().memoryPolicy(NO_CACHE, NO_STORE).into(mTarget);
    }

    protected Uri getUri() {
        String url = getIntent().getStringExtra(SysKey.URL);
        return Uri.parse(url);
    }

    @Override
    protected MDVRLibrary createVRLibrary() {
        return MDVRLibrary.with(this)
                .displayMode(MDVRLibrary.DISPLAY_MODE_NORMAL)
                .interactiveMode(MDVRLibrary.INTERACTIVE_MODE_TOUCH)
                .asBitmap(new MDVRLibrary.IBitmapProvider() {
                    @Override
                    public void onProvideBitmap(final MD360BitmapTexture.Callback callback) {
                        loadImage(getUri(), callback);
                    }
                })
                .listenTouchPick(new MDVRLibrary.ITouchPickListener() {
                    @Override
                    public void onHotspotHit(IMDHotspot hitHotspot, MDRay ray) {
                        Log.d("BitmapPlayerActivity", "Ray:" + ray + ", hitHotspot:" + hitHotspot);
                    }
                })
                .pinchEnabled(true)
                .projectionFactory(new CustomProjectionFactory())
                .build(R.id.gl_surface_view);
    }
}
