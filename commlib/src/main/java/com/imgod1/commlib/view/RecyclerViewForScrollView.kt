package com.imgod1.commlib.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * @author gaokang
 * @version 1.0 2019/7/2 16:54
 * @update gaokang 2019/7/2 16:54
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
class RecyclerViewForScrollView: RecyclerView{

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {

        val expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2,
                View.MeasureSpec.AT_MOST)
        super.onMeasure(widthSpec, expandSpec)

    }

}