package com.sir.app.md.vr;

import com.asha.vrlib.strategy.projection.AbsProjectionStrategy;
import com.asha.vrlib.strategy.projection.IMDProjectionFactory;
import com.asha.vrlib.strategy.projection.MultiFishEyeProjection;

/**
 * Created by zhuyinan on 2016/9/11
 * Contact by 445181052@qq.com
 */
public class CustomProjectionFactory implements IMDProjectionFactory {

    public static final int CUSTOM_PROJECTION_FISH_EYE_RADIUS_VERTICAL = 9611;

    @Override
    public AbsProjectionStrategy createStrategy(int mode) {
        switch (mode) {
            case CUSTOM_PROJECTION_FISH_EYE_RADIUS_VERTICAL:
                return new MultiFishEyeProjection(0.745f, false);
            default:
                return null;
        }
    }
}
