package com.golong.commlib.net;

/**
 * @author Andy
 * @date   2019/3/25 14:15
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    ResponseCode.java
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
