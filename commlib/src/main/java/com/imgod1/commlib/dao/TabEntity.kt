package com.imgod1.commlib.dao

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * @author Andy
 * @date   2018/11/1 14:16
 * Desc:
 */
class TabEntity : CustomTabEntity {

    private var tabTitle = ""
    fun setTabTitle(title: String) {

        this.tabTitle = title
    }

    override fun getTabUnselectedIcon(): Int {
        return 0
    }

    override fun getTabSelectedIcon(): Int {
        return 0
    }

    override fun getTabTitle(): String {
        return tabTitle
    }
}