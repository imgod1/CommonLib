package com.imgod1.commlib.base

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.webkit.JavascriptInterface
import com.imgod1.commlib.R
import com.imgod1.commlib.observablewebview.ObservableScrollViewCallbacks
import com.imgod1.commlib.observablewebview.ObservableWebView
import com.imgod1.commlib.observablewebview.ScrollState
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.fragment_web_view.*
import kotlinx.android.synthetic.main.top_comm_title.*

/**
 * @author gaokang
 * @version 1.0 2019/7/2 16:25
 * @update gaokang 2019/7/2 16:25
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
class WebViewFragment : Fragment(), ObservableScrollViewCallbacks {
    private var isContinue = false

    companion object {
        var TITLE = "TITLE"
        var URL = "URL"

        fun newInstance(url: String): WebViewFragment {
            var fragment = WebViewFragment()
            val b = Bundle()
            b.putString(URL, url)
            fragment.arguments = b
            return fragment
        }
    }

    var mRootView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(R.layout.fragment_web_view, null, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initWeb()
    }

    private fun initView() {
        val url = arguments?.getString(URL)
        if (!TextUtils.isEmpty(url)) {
            load2Web(url!!)
        } else {
            Log.e("WebViewFragment", "initView:加载的网址为空")
        }
    }

    var webview1: ObservableWebView? = null
    private fun initWeb() {
        webview1 = ObservableWebView(context)
        flayout_webview.addView(webview1)
        val webSettings = webview1!!.settings
        webSettings.cacheMode = WebSettings.LOAD_NORMAL
        webSettings.databaseEnabled = true
        webSettings.useWideViewPort = true
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.builtInZoomControls = false
        webSettings.loadsImagesAutomatically = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webview1!!.setScrollViewCallbacks(this)
        //Js调Java
        webview1!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(p0: WebView?, newProgress: Int) {
                //大于80的进度的时候,放慢速度加载,否则交给自己加载
                if (newProgress >= 80) {
                    //拦截webView自己的处理方式
                    if (isContinue) {
                        return;
                    }
                    mTopProgress.setCurProgress(100, 600) {
                        mTopProgress.setNormalProgress(100)
                        hideProgressWithAnim()
                        isContinue = false;
                    }
                    isContinue = true;
                } else {
                    mTopProgress.setNormalProgress(newProgress);
                }
            }

            //拦截H5
            override fun onReceivedTitle(p0: WebView?, p1: String?) {
                super.onReceivedTitle(p0, p1)
                if (!p1.isNullOrEmpty()) {
                    tvTitle.text = p1
                }
            }
        }
        webview1!!.addJavascriptInterface(JsInterface(), "control")

    }

    private fun load2Web(url: String) {
        webview1!!.loadUrl(url)
        webview1!!.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                mTopProgress.visibility = View.VISIBLE
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                mTopProgress.visibility = View.GONE
            }
        }
    }

    inner class JsInterface {
        @JavascriptInterface
        fun javaReplay(parent_id: String, receive_id: String, type_: String) {

        }
    }

    /**
     * 隐藏加载对话框
     */
    private fun hideProgressWithAnim() {
        val animation = getDismissAnim(context!!);
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                mTopProgress.visibility = View.GONE;
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        });
        mTopProgress.startAnimation(animation);
    }

    /**
     * 获取消失的动画
     *
     * @param context
     * @return
     */
    private fun getDismissAnim(context: Context): AnimationSet {
        val dismiss = AnimationSet(context, null)
        val alpha = AlphaAnimation(1.0f, 0.0f)
        alpha.duration = 500
        dismiss.addAnimation(alpha)
        return dismiss
    }

    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
    }

    override fun onDownMotionEvent() {
    }

    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
    }

    override fun onPause() {
        super.onPause()
        webview1?.onPause()
        webview1?.pauseTimers()
    }

    override fun onResume() {
        super.onResume()
        webview1?.onResume()
        webview1?.resumeTimers()
    }

    override fun onDestroy() {

        if (webview1 != null) {
            webview1?.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);//加载空地址
            webview1?.clearCache(true);
            webview1?.clearHistory();
            val viewGroup: ViewGroup = webview1?.parent as ViewGroup
            viewGroup.removeView(webview1)
            webview1?.destroy();
            flayout_webview.removeAllViews()
        }
        super.onDestroy()
    }

    fun getWebView(): ObservableWebView {
        return webview1!!
    }

}