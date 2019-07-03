package com.imgod1.commonlib

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.imgod1.commlib.common.ServerConfig
import com.imgod1.commlib.util.CommonLib

/**
 * MyApp.java。
 *
 * @author gaokang
 * @date 2019/6/15 12:02
 * @update gaokang 2019/6/15 12:02
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CommonLib.initCommonLib(applicationContext,ServerConfig.SERVER_HOST)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}