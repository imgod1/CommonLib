package com.imgod1.commlib.base;

import com.imgod1.commlib.encrypt.base.TextUtils;
import com.imgod1.commlib.user.UserInfoManager;

/**
 * BaseRequest.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:24
 * @update gaokang 2019/7/2 16:24
 * @updateDes
 * @include {@link }
 * @used {@link }
 */

public class BaseRequest extends MapParamsRequest{
    private String token = UserInfoManager.getInstance().getToken();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    protected void putParams() {
        if (!TextUtils.isEmpty(token)) {
            params.put("token", token);
        }
    }
}
