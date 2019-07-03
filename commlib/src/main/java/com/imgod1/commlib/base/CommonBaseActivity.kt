package com.imgod1.commlib.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.imgod1.commlib.util.ToastShow
import com.kongzue.dialog.v3.WaitDialog

/**
 * CommonBaseActivity.java。
 *
 * @author gaokang
 * @date 2019/7/3 9:35
 * @update gaokang 2019/7/3 9:35
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
abstract class CommonBaseActivity : AppCompatActivity(), BaseView, IInitView {
    private var mActivity: AppCompatActivity? = null
    public fun getActivity(): AppCompatActivity {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        Log.e("CommonBaseActivity:" + javaClass.simpleName, "onCreate")
        mActivity = this
        initViews()
        initData()
    }

    override fun showLoading() {
        WaitDialog.show(getActivity(), "")
    }

    override fun hideLoading() {
        WaitDialog.dismiss()
    }

    override fun onError(code: String?, msg: String?) {
        hideLoading()
        ToastShow.showMessage(msg)
    }
}