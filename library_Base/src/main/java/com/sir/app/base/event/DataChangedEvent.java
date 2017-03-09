package com.sir.app.base.event;

/**
 * Created by zhuyinan on 2016/12/8.
 * Contact by 445181052@qq.com
 */

public class DataChangedEvent {

    private String id;
    private String content;

    public DataChangedEvent(){

    }

    public DataChangedEvent(String content){
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
