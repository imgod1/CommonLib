package com.imgod1.commlib.net;

/**
 * ResponseCode.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:41
 * @update gaokang 2019/7/2 16:41
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public interface ResponseCode {
    String SUCCESS = "0";
    /**
     * token失效
     */
    String TOKEN_EXPIRE = "4000";
    /**
     * 参数错误
     */
    String ARGUMENT_ERROR = "4001";
    /**
     * 签名错误
     */
    String SIGNATURE_ERROR = "4002";
    /**
     * 访问过期
     */
    String TIME_ERROR = "4003";
}
