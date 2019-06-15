package com.imgod1.commonlib

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

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
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}