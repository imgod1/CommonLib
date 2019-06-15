package com.golong.commlib.base;

import com.golong.commlib.util.GsonUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Andy
 * @date   2019/3/25 14:03
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    PatchRequest.java
 */

public class PatchRequest {

    private MapParamsRequest request;

    public PatchRequest(MapParamsRequest request) {
        this.request = request;
    }

    public RequestBody getRequestBody() {
        String toJsonStr = GsonUtil.toJsonStr(request.params());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), toJsonStr);
        return requestBody;
    }
}
