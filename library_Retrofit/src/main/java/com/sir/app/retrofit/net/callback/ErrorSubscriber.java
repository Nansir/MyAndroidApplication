package com.sir.app.retrofit.net.callback;

import com.sir.app.retrofit.net.exception.ResponseThrowable;

import rx.Subscriber;

public abstract class ErrorSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof ResponseThrowable) {
            onError((ResponseThrowable) e);
        } else {
            onError(new ResponseThrowable(e, 1000));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ResponseThrowable ex);
}

