package com.coolweather.coolweatherjetpack.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.coolweather.coolweatherjetpack.ui.common.LoadingDialog

open class BaseActivity : AppCompatActivity(){
    private var mLoadingDialog : LoadingDialog?=null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
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

            mLoadingDialog?.hide()
        }
    }
}