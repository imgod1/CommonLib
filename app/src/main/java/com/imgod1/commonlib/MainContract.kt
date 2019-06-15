package com.imgod1.commonlib

import com.golong.commlib.base.BaseResponse
import com.golong.commlib.base.BaseView
import com.golong.commlib.base.IClear

interface MainContract {

    interface Presenter : IClear {
        fun getData()
    }

    interface View : BaseView {
        fun onGetDataSuccess(resq: BaseResponse<Any>)
    }
}