package com.imgod1.commonlib

import android.content.Context
import com.golong.commlib.base.BaseRequest
import com.golong.commlib.base.BaseResponse
import com.golong.commlib.net.SuccessConsumer
import com.imgod1.commonlib.presenter.PresenterWrapper
import io.reactivex.functions.Consumer

/**
 * MainPresenter.java。
 *
 * @author gaokang
 * @date 2019/6/15 11:43
 * @update gaokang 2019/6/15 11:43
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
class MainPresenter(context: Context, view: MainContract.View) : MainContract.Presenter,
    PresenterWrapper<MainContract.View>(context, view) {
    override fun getData() {
        var req = BaseRequest()
        add(
            mService.getOverlay(req.params())
                .compose(getTransformer())
                .subscribe(object : SuccessConsumer<BaseResponse<Any>>(mView) {
                    override fun onSuccess(response: BaseResponse<Any>) {
                        mView.onGetDataSuccess(response)
                    }
                }, Consumer<Throwable> { throwable -> throwable.printStackTrace() })
        )
    }
}