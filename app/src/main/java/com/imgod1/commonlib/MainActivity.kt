package com.imgod1.commonlib

import android.widget.Toast
import com.imgod1.commlib.base.BaseResponse
import com.imgod1.commlib.base.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CommonBaseActivity(), MainContract.View {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
    }

    override fun initData() {
        mPresenter = MainPresenter(this, this)
        btn_request.setOnClickListener {
            showLoading()
            btn_request.postDelayed({
                mPresenter?.getData()
            }, 3000)
        }
    }

    override fun onGetDataSuccess(resq: BaseResponse<Any>) {
        hideLoading()
        Toast.makeText(this, resq.msg, Toast.LENGTH_LONG).show()
    }

    var mPresenter: MainPresenter? = null
}
