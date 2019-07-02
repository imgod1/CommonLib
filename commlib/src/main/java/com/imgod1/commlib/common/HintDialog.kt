package com.imgod1.commlib.common

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.SpannableString
import android.view.View
import android.view.Window
import android.widget.TextView
import com.imgod1.commlib.R
import com.imgod1.commlib.interf.OnDialogClickListener
import java.util.*
/**
 * .java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:32
 * @update gaokang 2019/7/2 16:32
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
object HintDialog {
    private var lastTime: Long = 0

    val isReplayClick: Boolean
        @Synchronized get() {
            val currentTime = Calendar.getInstance().timeInMillis
            val isClick: Boolean
            isClick = currentTime - lastTime < 2000
            lastTime = currentTime
            return isClick
        }


    private val mListener: OnDialogItemClickListener? = null

    fun showHintDialog(
        context: Activity,
        title: String?,
        content: String?,
        sureText: String?,
        cancleText: String?,
        listener: OnDialogClickListener?,
        ifCancleView: Boolean = true,
        isCancelable: Boolean = false,
        contentColor: Int = Color.parseColor("#333333"),
        sureTextColor: Int = context.resources.getColor(R.color.colorPrimary),
        cancleTextColor: Int = Color.parseColor("#666666")
    ) {

        val dialog = Dialog(context)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        dialog.window!!.setBackgroundDrawableResource(R.drawable.bg_pic_save)
        dialog.setCancelable(isCancelable)
        dialog.setCanceledOnTouchOutside(isCancelable)
        dialog.setContentView(R.layout.dialog_hint_item)
        val attributes = dialog.window!!.attributes
        val windowManager = context.windowManager
        val display = windowManager.defaultDisplay
        attributes.width = (display.width * 0.8).toInt()
        attributes.dimAmount = 0.5f
        dialog.window!!.attributes = attributes
        val tvContent = dialog.window!!.findViewById<TextView>(R.id.tv_content)
        val tvSure = dialog.window!!.findViewById<TextView>(R.id.tv_sure)
        val tvTitle = dialog.window!!.findViewById<TextView>(R.id.tv_title)
        val tvCancle = dialog.window!!.findViewById<TextView>(R.id.tv_cancel)
        tvContent.setTextColor(contentColor)
        tvCancle.setTextColor(cancleTextColor)
        tvSure.setTextColor(sureTextColor)
        val view = dialog.window!!.findViewById<View>(R.id.view)
        if (title != null && "" != title.trim { it <= ' ' }) {
            tvTitle.text = title
        }
        if (content != null && "" != content.trim { it <= ' ' }) {
            tvContent.text = content
        }
        if (sureText != null && "" != sureText.trim { it <= ' ' }) {
            tvSure.text = sureText
        }
        if (cancleText != null && "" != cancleText.trim { it <= ' ' }) {
            tvCancle.text = cancleText
        }
        if (ifCancleView) {
            tvCancle.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
        } else {
            tvCancle.visibility = View.GONE
            view.visibility = View.GONE
        }

        tvCancle.setOnClickListener { v ->
            listener?.onCancleClick()
            dialog.dismiss()
        }
        tvSure.setOnClickListener { v ->
            listener?.onSureClick()
            dialog.dismiss()
        }
        dialog.show()
    }

    fun showSpanDialog(
        context: Activity, title: String?, content: SpannableString?, sureText: String?,
        cancleText: String?, ifCancleView: Boolean, isCancelable: Boolean, listener: OnDialogClickListener?
        , contentColor: Int = context.resources.getColor(R.color.black),
        sureTextColor: Int = Color.parseColor("#00a0e9"),
        cancleTextColor: Int = Color.parseColor("#666666")
    ) {

        val dialog = Dialog(context)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        dialog.window!!.setBackgroundDrawableResource(R.drawable.bg_pic_save)
        dialog.setCancelable(isCancelable)
        dialog.setCanceledOnTouchOutside(isCancelable)
        dialog.setContentView(R.layout.dialog_hint_item)
        val attributes = dialog.window!!.attributes
        val windowManager = context.windowManager
        val display = windowManager.defaultDisplay
        attributes.width = (display.width * 0.8).toInt()
        attributes.dimAmount = 0.5f
        dialog.window!!.attributes = attributes
        val tvContent = dialog.window!!.findViewById<View>(R.id.tv_content) as TextView
        val tvSure = dialog.window!!.findViewById<View>(R.id.tv_sure) as TextView
        val tvTitle = dialog.window!!.findViewById<View>(R.id.tv_title) as TextView
        val tvCancle = dialog.window!!.findViewById<View>(R.id.tv_cancel) as TextView
        tvCancle.setTextColor(cancleTextColor)
        tvSure.setTextColor(sureTextColor)
        tvContent.setTextColor(contentColor)
        val view = dialog.window!!.findViewById<View>(R.id.view)
        if (title != null && "" != title.trim { it <= ' ' }) {
            tvTitle.text = title
        }
        if (content != null) {
            tvContent.text = content
        }
        if (sureText != null && "" != sureText.trim { it <= ' ' }) {
            tvSure.text = sureText
        }
        if (cancleText != null && "" != cancleText.trim { it <= ' ' }) {
            tvCancle.text = cancleText
        }
        if (ifCancleView) {
            tvCancle.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
        } else {
            tvCancle.visibility = View.GONE
            view.visibility = View.GONE
        }

        tvCancle.setOnClickListener { v ->
            listener?.onCancleClick()
            dialog.dismiss()
        }
        tvSure.setOnClickListener { v ->
            listener?.onSureClick()
            dialog.dismiss()
        }
        dialog.show()
    }

    interface OnDialogItemClickListener {
        fun onItemClick(position: Int, datas: Array<String>?, view: View)
    }
}
