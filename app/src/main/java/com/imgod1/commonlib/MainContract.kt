package com.imgod1.commonlib

import com.imgod1.commlib.base.BaseResponse
import com.imgod1.commlib.base.BaseView
import com.imgod1.commlib.base.IClear

interface MainContract {

    interface Presenter : IClear {
        fun getData()
    }

    interface View : BaseView {
        fun onGetDataSuccess(resq: BaseResponse<Any>)
    }
}