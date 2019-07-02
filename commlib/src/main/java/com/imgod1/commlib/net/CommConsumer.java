package com.imgod1.commlib.net;
import io.reactivex.functions.Consumer;

/**
 * CommConsumer.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:41
 * @update gaokang 2019/7/2 16:41
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public abstract class CommConsumer<T> implements Consumer<T> {

    @Override
    public void accept(T t) throws Exception {
        onSuccess(t);
    }
    public abstract void onSuccess(T data);
}
