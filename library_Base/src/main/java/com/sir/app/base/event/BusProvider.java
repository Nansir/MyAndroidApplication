package com.sir.app.base.event;

import com.squareup.otto.Bus;

import java.util.ArrayList;

/**
 * Created by zhuyinan on 2016/12/8.
 * Contact by 445181052@qq.com
 */

public class BusProvider extends Bus {

    private static BusProvider busProvider;
    private ArrayList registeredObjects = new ArrayList<>();

    private BusProvider() {

    }

    public static BusProvider getBusInstance() {
        if (busProvider == null) {
            synchronized (BusProvider.class) {  //加上sunchronized来防止并发
                busProvider = new BusProvider();
            }
        }
        return busProvider;
    }

    @Override
    public void register(Object object) {
        if (!registeredObjects.contains(object)) {
            registeredObjects.add(object);
            super.register(object);
        }
    }

    @Override
    public void unregister(Object object) {
        if (registeredObjects.contains(object)) {
            registeredObjects.remove(object);
            super.unregister(object);
        }
    }

}
