package com.imgod1.commonlib

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.imgod1.commlib.base.BaseResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    override fun onGetDataSuccess(resq: BaseResponse<Any>) {
        Toast.makeText(this, resq.msg, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(code: String?, msg: String?) {
    }

    var mPresenter: MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter(this, this)
        btn_request.setOnClickListener {
            mPresenter?.getData()
        }
    }
}
