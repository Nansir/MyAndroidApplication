package com.sir.app.base;

import java.util.Collection;

/**
 * Created by zhuyinan on 2016/12/6.
 * Contact by 445181052@qq.com
 */

public interface IBaseAdapter<T> {

    public T getItem(T data);

    public void addItem(T data);

    public void removeItem(int position);

    public void updateItem(int position, T data);

    public void shift(int fromPosition, int toPosition);

    public void addItem(Collection<? extends T> list);


}
