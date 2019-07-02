package com.imgod1.commlib.dao

import com.flyco.tablayout.listener.CustomTabEntity

/**
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:36
 * @update gaokang 2019/7/2 16:36
 * @updateDes
 * @include {@link }
 * @used {@link }
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