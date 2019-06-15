package com.imgod1.commonlib.presenter;

import com.golong.commlib.base.BaseResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface ApiService {

    /**
     * 获取首页覆盖图片
     */
    @GET("PublicFun.getAppStatus")
    Observable<BaseResponse<Object>> getOverlay(@QueryMap Map<String, Object> map);
}
