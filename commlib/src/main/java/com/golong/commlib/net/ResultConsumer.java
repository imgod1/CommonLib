package com.golong.commlib.net;

import com.golong.commlib.base.BaseResponse;
import com.golong.commlib.base.BaseView;
import com.golong.commlib.user.UserInfoManager;
import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;

/**
 * @author Andy
 * @date 2019/3/25 15:16
 * @link {http://blog.csdn.net/andy_l1}
 * Desc:    ResultConsumer.java
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
