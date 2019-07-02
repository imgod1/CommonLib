package com.imgod1.commlib.base;

import android.content.Context;

import com.imgod1.commlib.net.ErrorConsumer;
import com.imgod1.commlib.util.NetUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * BasePresenter.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:23
 * @update gaokang 2019/7/2 16:23
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class BasePresenter<V extends BaseView> implements IClear {
    protected V mView;
    protected Context mContext;
    protected final long RETRY_TIMES = 0;
    protected final CompositeDisposable mDisposables = new CompositeDisposable();

    public BasePresenter(Context context, V v) {
        mContext = context;
        mView = v;
    }

    protected <T> ObservableTransformer<BaseResponse<T>, BaseResponse<T>> getTransformer() {
        return new ObservableTransformer<BaseResponse<T>, BaseResponse<T>>() {
            @Override
            public ObservableSource<BaseResponse<T>> apply(Observable<BaseResponse<T>> observable) {
                return observable.retry(RETRY_TIMES).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!NetUtil.isConnected(mContext)) {
                                    disposable.dispose();
//                                    Toast.makeText(mContext, "网络连接异常,请检查网络", Toast.LENGTH_SHORT).show();
                                    mView.onError("2000", "网络连接异常,请检查网络");
                                    mView.hideLoading();
                                }
                            }
                        })
                        .doOnError(new ErrorConsumer(mView))
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                            }
                        });
            }
        };
    }


    protected <T> ObservableTransformer<T, T> getCommTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.retry(RETRY_TIMES).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!NetUtil.isConnected(mContext)) {
                                    disposable.dispose();
//                                    Toast.makeText(mContext, "网络连接异常,请检查网络", Toast.LENGTH_SHORT).show();
                                    mView.onError("2000", "网络连接异常,请检查网络");
                                    mView.hideLoading();
                                }
                            }
                        })
                        .doOnError(new ErrorConsumer(mView))
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                            }
                        });
            }
        };
    }

    protected void add(Disposable disposable) {
        mDisposables.add(disposable);
    }

    @Override
    public void clear() {
        mDisposables.clear();
    }

}
