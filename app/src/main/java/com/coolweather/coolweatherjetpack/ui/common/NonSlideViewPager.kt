package com.coolweather.coolweatherjetpack.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NonSlideViewPager : ViewPager {
    constructor(ctx: Context):super(ctx)
    constructor(ctx: Context, attributes: AttributeSet) : super(ctx,attributes)

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}