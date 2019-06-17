package com.imgod1.commlib.net;
import io.reactivex.functions.Consumer;

/**
 * @author Andy
 * @date 2019/3/26 12:00
 * Desc:
 */
public abstract class CommConsumer<T> implements Consumer<T> {

    @Override
    public void accept(T t) throws Exception {
        onSuccess(t);
    }
    public abstract void onSuccess(T data);
}
