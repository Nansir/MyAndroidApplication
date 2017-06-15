package com.sir.app.base.event;

/**
 * Created by zhuyinan on 2016/12/8.
 * Contact by 445181052@qq.com
 */

public class BusEvent {

    public BusEvent() {

    }

    public BusEvent(String json) {
        this.jsonStr = json;
    }

    public BusEvent(int id) {
        this.id = id;
    }

    public BusEvent(int id, String json) {
        this.id = id;
        this.jsonStr = json;
    }

    public BusEvent(int id, int digit) {
        this.id = id;
        this.digit = digit;
    }

    public int id;

    public String jsonStr;

    public int digit;

}
