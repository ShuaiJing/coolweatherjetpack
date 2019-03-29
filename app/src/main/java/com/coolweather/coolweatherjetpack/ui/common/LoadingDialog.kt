package com.coolweather.coolweatherjetpack.ui.common

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.coolweather.coolweatherjetpack.R

class LoadingDialog(context: Context) : Dialog(context, R.style.WinDialog) {

    private val mTextView: TextView?
    private val mImage: ImageView
    internal var animator: ObjectAnimator? = null

    init {
        setContentView(R.layout.loading_dialog)
        mTextView = findViewById<View>(android.R.id.message) as TextView
        mImage = findViewById(R.id.loading)
        val animator = ObjectAnimator.ofFloat(mImage, "rotation", 0f, 360f).setDuration(4000)
        animator.repeatCount = 10000
        animator.repeatCount = ObjectAnimator.RESTART
        animator.start()
    }

    override fun show() {
        super.show()
        animator = ObjectAnimator.ofFloat(mImage, "rotation", 0f, 359f).setDuration(3000)
        animator!!.interpolator = LinearInterpolator()
        animator!!.repeatCount = ValueAnimator.INFINITE
        animator!!.repeatMode = ObjectAnimator.RESTART
        animator!!.start()
    }

    override fun dismiss() {
        super.dismiss()
    }

    fun setText(s: String) {
        if (mTextView != null) {
            mTextView.text = s
            mTextView.visibility = View.VISIBLE

        }
        if (animator != null) {
            animator!!.cancel()
        }
    }

    fun setText(res: Int) {
        if (mTextView != null) {
            mTextView.setText(res)
            mTextView.visibility = View.VISIBLE
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (event.action == MotionEvent.ACTION_DOWN) {
            false
        } else super.onTouchEvent(event)
    }
}
