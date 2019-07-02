package com.imgod1.commlib.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.imgod1.commlib.R
import com.imgod1.commlib.util.StatusBarUtil
import kotlinx.android.synthetic.main.top_comm_title.*

/**
 * @author gaokang
 * @version 1.0 2019/7/2 16:25
 * @update gaokang 2019/7/2 16:25
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
class WebViewActivity : AppCompatActivity() {
    private var isContinue = false

    companion object {
        //默认的类型
        var TYPE_DEFAULT = "0"

        var TITLE = "TITLE"
        var URL = "URL"
        var TYPE = "TYPE"

        fun actionStart(context: Context, title: String, url: String) {
            actionStart(context, title, url, TYPE_DEFAULT)
        }

        fun actionStart(context: Context, title: String, url: String, type: String) {
            var intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(TITLE, title)
            intent.putExtra(URL, url)
            intent.putExtra(TYPE, type)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_web_view)
        initView()
    }

    var webViewFragment: WebViewFragment? = null
    private fun initView() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white), 0)
        StatusBarUtil.setLightMode(this)
        val title = intent.getStringExtra(TITLE)
        val url = intent.getStringExtra(URL)
        tvTitle.text = if (title.isEmpty()) "Web" else title
        back.setOnClickListener {
            if (webViewFragment?.getWebView()!!.canGoBack()) {
                webViewFragment?.getWebView()!!.goBack()
            } else {
                finish()
            }
        }
        load2Web(url)
    }


    private fun load2Web(url: String) {
        webViewFragment = WebViewFragment.newInstance(url)
        supportFragmentManager.beginTransaction().replace(R.id.flayout_main, webViewFragment!!).commit()
    }

}