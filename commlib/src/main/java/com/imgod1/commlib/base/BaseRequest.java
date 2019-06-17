package com.imgod1.commlib.base;

import com.imgod1.commlib.encrypt.base.TextUtils;
import com.imgod1.commlib.user.UserInfoManager;

/**
 * @author Andy
 * @date   2019/3/26 15:21
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    BaseRequest.java
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
