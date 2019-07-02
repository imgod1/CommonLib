package com.imgod1.commlib.base

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.webkit.JavascriptInterface
import com.imgod1.commlib.R
import com.imgod1.commlib.observablewebview.ObservableScrollViewCallbacks
import com.imgod1.commlib.observablewebview.ScrollState
import com.imgod1.commlib.util.StatusBarUtil
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.layout_web_view.*
import kotlinx.android.synthetic.main.top_comm_title.*

/**
 * @author gaokang
 * @version 1.0 2019/7/2 16:25
 * @update gaokang 2019/7/2 16:25
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
class WebViewActivity : AppCompatActivity(), ObservableScrollViewCallbacks {
    private var isContinue = false

    companion object {
        var TITLE = "TITLE"
        var URL = "URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_web_view)
        initView()
        initWeb()
    }

    private fun initView() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white), 0)
        StatusBarUtil.setLightMode(this)
        val title = intent.getStringExtra(TITLE)
        val url = intent.getStringExtra(URL)
        tvTitle.text =  if (title.isEmpty()) "Web" else title
        back.setOnClickListener {
            if (webview1.canGoBack()) {
                webview1.goBack()
            } else {
                finish()
            }
        }
        if (url != null)
            load2Web(url)
    }


    private fun initWeb() {
        val webSettings = webview1.settings
        webSettings.cacheMode = WebSettings.LOAD_NORMAL
        webSettings.databaseEnabled = true
        webSettings.useWideViewPort = true
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.builtInZoomControls = false
        webSettings.loadsImagesAutomatically = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webview1.setScrollViewCallbacks(this)
        //Js调Java
        webview1.webChromeClient = object : WebChromeClient() {
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
                    tvTitle.text=p1
                }
            }
        }
        webview1.addJavascriptInterface(JsInterface(), "control")

    }

    private fun load2Web(url: String) {
        webview1.loadUrl(url)
        webview1.webViewClient = object : WebViewClient() {
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
        val animation = getDismissAnim(this);
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (webview1.canGoBack()) {
                webview1.goBack()
                return true
            } else {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (webview1.canGoBack()) {
                webview1.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        super.onPause()
        webview1.onPause()
        webview1.pauseTimers()
    }

    override fun onResume() {
        super.onResume()
        webview1.onResume()
        webview1.resumeTimers()
    }

    override fun onDestroy() {

        if (webview1 != null) {
            webview1.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);//加载空地址
            webview1.clearCache(true);
            webview1.clearHistory();
            val viewGroup: ViewGroup = webview1.parent as ViewGroup
            viewGroup.removeView(webview1)
            webview1.destroy();
        }
        super.onDestroy()
    }
}