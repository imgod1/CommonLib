package com.golong.commlib.net;

import com.golong.commlib.base.BaseResponse;
import com.golong.commlib.base.BaseView;
import com.golong.commlib.user.UserInfoManager;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.CrashReport;
import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;

/**
 * Created by Snow on 2017/4/7.
 * Description:
 */

public abstract class SuccessConsumer<T> implements Consumer<T> {

    private BaseView mBaseView;

    public SuccessConsumer(BaseView baseView) {
        mBaseView = baseView;
    }

    @Override
    public void accept(T response) throws Exception {
        BaseResponse baseResponse = (BaseResponse) response;
        String code = baseResponse.getCode();
        String msg=baseResponse.getMsg();
        if (code.equals(ResponseCode.SUCCESS) || "200".equals(code)) {
            onSuccess(response);
        } else {
            switch (code) {
                case ResponseCode.TOKEN_EXPIRE:
                    if (!UserInfoManager.isLoginDisable) {
                        UserInfoManager.isLoginDisable = true;
                        UserInfoManager.getInstance().setToken("");
                        UserInfoManager.getInstance().clearData();
                        EventBus.getDefault().post(new TokenExpireEvent());
                    }
                    break;
                case ResponseCode.ARGUMENT_ERROR:
                    return;
                case ResponseCode.SIGNATURE_ERROR:
                    return;
                case ResponseCode.TIME_ERROR:
                    return;
                default:
            }
            mBaseView.onError(code, msg);
            mBaseView.hideLoading();
        }
    }

    public abstract void onSuccess(T response);
}
