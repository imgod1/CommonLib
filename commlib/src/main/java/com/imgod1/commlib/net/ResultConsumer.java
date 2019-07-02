package com.imgod1.commlib.net;

import com.imgod1.commlib.base.BaseResponse;
import com.imgod1.commlib.base.BaseView;
import com.imgod1.commlib.user.UserInfoManager;
import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;

/**
 * ResultConsumer.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:41
 * @update gaokang 2019/7/2 16:41
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public abstract class ResultConsumer<T> implements Consumer<T> {

    private BaseView mBaseView;

    public ResultConsumer(BaseView baseView) {
        mBaseView = baseView;
    }

    @Override
    public void accept(T response) throws Exception {
        BaseResponse baseResponse = (BaseResponse) response;
        String code = baseResponse.getCode();
        if (code.equals(ResponseCode.SUCCESS)) {
            onSuccess(response);
        } else {
            onError(((BaseResponse) response).getCode(), ((BaseResponse) response).getMsg());
            mBaseView.hideLoading();
            switch (code) {
                case ResponseCode.TOKEN_EXPIRE: {
//                case ResponseCode.ACCOUNT_DISABLE:
                    if (!UserInfoManager.isLoginDisable) {
                        UserInfoManager.isLoginDisable = true;
                        UserInfoManager.getInstance().setToken("");
                        EventBus.getDefault().post(new TokenExpireEvent());
                    }

                    break;
                }
                default:
            }
            mBaseView.onError(code, baseResponse.getMsg());
        }
    }

    public abstract void onSuccess(T response);

    public abstract void onError(String code, String msg);
}
