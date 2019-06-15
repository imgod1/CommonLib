package com.golong.commlib.common

/**
 * Created by Snow on 2017/10/9.
 * Description:
 */
object Constant {

    val COUNT_DOWN_TIME: Long = 60
    val PAGE_SIZE = 12
    val PAGE_SIZE1 = 10
    val JPUSH_SEQUENCE = 1000
    val SERVICE_PHONE = "service_phone"

    val WEIXIN_APP_ID = "wx507832aa454c200b"
    val WEIXIN_APP_SECRET = "badb9b8d86dccba482118ad8a1dcec1d"

    val BUGLY_APP_ID = "e6b9ccecb2"

    //跨境商品一次最多只能买6个
    val CROSS_BORDER_GOODS_MAX_BUY_COUNT = 6

    //礼包
    val GOODS_TYPE_GIFT = "8"
    //跨境商品
    val GOODS_TYPE_CROSS_BORDER = "1"
    //一般商品
    val GOODS_TYPE_NORMAL = "2"

    //优惠券可用
    val COUPON_VALID = "0"
    //优惠券不可用
    val COUPON_INVALID = "-1"


    //点击购买的类型 商品详情页的立即购买
    val BUY_TYPE_GOODS_DETAIL = "1"
    //点击购买的类型 购物车结算
    val BUY_TYPE_CART = "2"
}