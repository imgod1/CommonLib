package com.golong.commlib.base


import com.golong.commlib.common.ServerConfig
import com.golong.commlib.encrypt.base.TextUtils
import com.golong.commlib.encrypt.oneway.SHAUtil
import com.golong.commlib.util.AppUtil

import java.util.*
import kotlin.collections.LinkedHashMap

/**
 * @author Andy
 * @date   2019/3/25 14:03
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    MapParamsRequest.java
 */
 abstract class MapParamsRequest {
    @JvmField
    var params: TreeMap<String, Any>

    init {
        this.params = TreeMap(Comparator { o1, o2 -> o1.compareTo(o2) })
    }

    fun params(): Map<String, Any> {
        params.clear()
        putParams()
        //        String s = String.valueOf(System.currentTimeMillis());
        //        String s1 = s.substring(0,s.length() - 3) + "000";
        params["timestamp"] = System.currentTimeMillis()
        var versionName = AppUtil.getVersionName()
        if (ServerConfig.isDebug) {
            versionName = "v$versionName"
        }
        params["from_platform"] = "android$versionName"
        val map = params.filterNot {
            it.value.toString().isEmpty()
        } as LinkedHashMap
        map["signature"] = sig(map)
        return map
    }

    private fun sig(params: Map<String, Any>): String {
        val sb = StringBuilder()
        val entrySet = params.entries
        for ((key, value) in entrySet) {
            if (TextUtils.isEmpty(value)) {
                continue
            }
            sb.append(key)
            sb.append("=")
            sb.append(value.toString() + "")
            sb.append("&")
        }
        sb.deleteCharAt(sb.length - 1)
        sb.append(ServerConfig.SECRET_KEY)
        return SHAUtil.sha1(sb.toString())
    }

    protected abstract fun putParams()
}
