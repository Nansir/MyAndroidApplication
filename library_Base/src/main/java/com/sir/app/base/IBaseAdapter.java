package com.sir.app.base;

import java.util.Collection;

/**
 * Created by zhuyinan on 2016/12/6.
 * Contact by 445181052@qq.com
 */

public interface IBaseAdapter<T> {

    public void clearAll();

    public T getItem(int position);

    public void addItem(T data);

    public void addItem(int location, T data);

    public void removeItem(int position);

    public void addItem(Collection<? extends T> list);

}
