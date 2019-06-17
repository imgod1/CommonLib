package com.imgod1.commlib.common

/**
 * @author Andy
 * @date   2018/7/3 13:11
 * Desc:    配置线上线下环境
 */
object ServerConfig {

    @JvmField
    val SERVER_HOST: String
    @JvmField
    val SECRET_KEY: String
    @JvmField
    val SERVER_NEW_HOST: String
    /**
     * true dev为预发布版本  false  cs1为测试版
     */
    const val isPre: Boolean = true
    /**
     * 控制线上和线下环境 debug输出的控制  true为测试环境 false 正式环境
     */
    const val isDebug: Boolean = true

    init {
        //测试环境
        if (isDebug) {
            if (isPre) {
                SERVER_HOST = "https://dev.qingym.cn/wxmall/"
            }else{
                //测试版
                SERVER_HOST = "http://cs1.qingym.cn:81/wxmall/"
            }
            SECRET_KEY = "5lrsW3nictfJPY8L14f"
            SERVER_NEW_HOST = "https://dev.qingym.cn/wxmall/"
            //正式环境
        } else {

            SERVER_HOST = "https://www.qingym.cn/wxmall/"
            SECRET_KEY = "5lrsW3nictfJPY8L14f"
            SERVER_NEW_HOST = "https://www.qingym.cn/wxmall/"
        }
    }

}
