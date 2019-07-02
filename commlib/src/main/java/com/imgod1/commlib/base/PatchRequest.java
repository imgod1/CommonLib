package com.imgod1.commlib.base;

import com.imgod1.commlib.util.GsonUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * PatchRequest.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:25
 * @update gaokang 2019/7/2 16:25
 * @updateDes
 * @include {@link }
 * @used {@link }
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
