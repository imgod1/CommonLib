package com.imgod1.commlib.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
abstract class CommonBaseFragment : Fragment(), BaseView, IInitView {
    var mRootView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(layoutResId, null, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    override fun showLoading() {
        if (activity is AppCompatActivity) {
            WaitDialog.show(activity as AppCompatActivity, "")
        }
    }

    override fun hideLoading() {
        WaitDialog.dismiss()
    }

    override fun onError(code: String?, msg: String?) {
        hideLoading()
        ToastShow.showMessage(msg)
    }
}