package com.imgod1.commlib.base


import com.imgod1.commlib.common.ServerConfig
import com.imgod1.commlib.encrypt.base.TextUtils
import com.imgod1.commlib.encrypt.oneway.SHAUtil
import com.imgod1.commlib.util.AppUtil
import java.util.*

/**
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:24
 * @update gaokang 2019/7/2 16:24
 * @updateDes
 * @include {@link }
 * @used {@link }
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
