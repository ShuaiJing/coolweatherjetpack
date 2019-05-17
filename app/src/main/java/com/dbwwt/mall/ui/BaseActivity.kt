package com.dbwwt.mall.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dbwwt.mall.R
import com.dbwwt.mall.ui.common.LoadingDialog
import com.dbwwt.mall.util.ScreenUtil


 abstract class BaseActivity : AppCompatActivity(){
    private var mLoadingDialog : LoadingDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(layoutId())
        super.onCreate(savedInstanceState)
        ScreenUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.white))
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun showLoading(text : String) {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(this)
        }
        mLoadingDialog?.show()
    }

    fun hideLoading() {
        if (mLoadingDialog!!.isShowing) {

            mLoadingDialog!!.hide()
        }
    }
    @LayoutRes
    abstract fun layoutId() : Int
}