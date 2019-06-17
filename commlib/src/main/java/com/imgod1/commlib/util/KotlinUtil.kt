package com.imgod1.commlib.util

import android.view.View
import com.imgod1.commlib.common.IToast
import com.imgod1.commlib.common.ServerConfig
import com.imgod1.commlib.common.XLog
import java.nio.charset.Charset


/**
 * Created by Snow on 2017/10/10.
 * Description:
 */
val TAG: String = "==="


fun toast(message: CharSequence) {

    val config = IToast.Builder()
        .setTextSize(16)
        .setCornerRadius(8)
        .build()
    if (message.toString().length < 18) {
        IToast.showShort(message, config)
    } else {
        IToast.showLong(message, config)
    }
}


fun log(log: Any) {
    if (ServerConfig.isDebug) {
        XLog.e(TAG, log)
    }
}

fun CharSequence.toUtf8(): String {
    return String(this.toString().toByteArray(), Charset.forName("UTF-8"))
}

/**
 * 判断字符串位是纯数字
 */
fun String.IsNum(): Boolean {
    return this.all {
        it.isDigit()
    }
}

fun View.setViewVisible(isVisibel: Boolean) {
    if (isVisibel) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun matchUrl(content: String): List<String> {
    val rg = Regex("src=\"([^\"]+)", RegexOption.IGNORE_CASE)
    val findAll = rg.findAll(content)
    val list = mutableListOf<String>()
    findAll.forEach {
        list.add(it.groupValues[1])
    }
    return list
}