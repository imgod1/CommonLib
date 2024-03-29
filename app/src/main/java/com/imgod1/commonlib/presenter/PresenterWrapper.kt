package com.imgod1.commonlib.presenter

import android.content.Context
import com.imgod1.commlib.base.BasePresenter
import com.imgod1.commlib.base.BaseView
import com.imgod1.commlib.net.NetClient

/**
 * @author Andy
 * @date   2019/3/26 11:20
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    PresenterWrapper.kt
 */
open class PresenterWrapper<V : BaseView>(context: Context, v: V) : BasePresenter<V>(context, v) {

    protected var mService: ApiService = NetClient.getInstance(ApiService::class.java)
}