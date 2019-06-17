package com.yfjj.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration

/**
 * @author Andy
 * @date 2018/11/20 15:10
 * Desc: 纵向优先滑动的rv
 */
class VPriorityRecyclerView : RecyclerView {
    private var rawY: Float = 0.toFloat()
    private var evRawY: Float = 0.toFloat()
    private var firstVisibleItemPosition: Int = 0
    private var lastVisibleItemPosition: Int = 0
    private var itemCount: Int = 0

    private var currentState: Int = 0
    private var mTouchSlop: Int = 0

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    private fun init(context: Context) {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onScrollStateChanged(state: Int) {
        this.currentState = state

    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        val manager = layoutManager as LinearLayoutManager
        firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
        lastVisibleItemPosition = manager.findLastVisibleItemPosition()


    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        val action = ev.action
        when (action) {

            MotionEvent.ACTION_DOWN -> {
                itemCount = adapter!!.itemCount
                parent.requestDisallowInterceptTouchEvent(true)
                rawY = ev.rawY
            }

            MotionEvent.ACTION_MOVE -> {
                evRawY = ev.rawY
                Log.e(TAG, "itemCount=$itemCount--->firstVisibleItemPosition=$firstVisibleItemPosition--->lastVisibleItemPosition=$lastVisibleItemPosition")
                Log.e(TAG, "evRawY - rawY =" + (evRawY - rawY))
                Log.e(TAG, "canScrollVertically(1)=" + canScrollVertically(1) + "----->canScrollVertically(-1)=" + canScrollVertically(-1))
                if (!canScrollVertically(1) && evRawY - rawY < mTouchSlop && lastVisibleItemPosition + 1 == itemCount) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else if (!canScrollVertically(-1) && evRawY - rawY > mTouchSlop && firstVisibleItemPosition == 0) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    companion object {
        private val TAG = "VpRecyclerView"
    }
}
