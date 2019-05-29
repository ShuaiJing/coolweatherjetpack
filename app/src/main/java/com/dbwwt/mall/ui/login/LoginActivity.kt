package com.dbwwt.mall.ui.login

import android.os.Bundle
import android.view.View

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dbwwt.mall.R
import com.dbwwt.mall.data.AccountRepository
import com.dbwwt.mall.data.Resource
import com.dbwwt.mall.data.network.CoolWeatherNetwork
import com.dbwwt.mall.ui.BaseActivity
import com.dbwwt.mall.ui.main.MainActivity
import com.dbwwt.mall.util.InjectorUtil
import com.dbwwt.mall.util.ScreenUtil
import com.dbwwt.mall.util.newIntent
import com.dbwwt.mall.util.showToast

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_toolbar.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity(),TextWatcher {
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun afterTextChanged(p0: Editable?) {
        if (phone_num.text.length==11 && sms_code.text.isNotEmpty()) {
            sign_in_button.isEnabled = true
        }

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    private lateinit var  viewModel:LoginViewModel
    private lateinit var mCountDownTimer: MyCountDownTimer
    private var token = ""
    private var code = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenUtil.setStatusBarColor(this,ContextCompat.getColor(this,R.color.white))
        // Set up the login form.
        toolbar.title = "登录"
        toolbar.setNavigationOnClickListener{finish()}
        viewModel = ViewModelProviders.of(this, InjectorUtil.getLoginModelFactory()).get(LoginViewModel::class.java)
        mCountDownTimer = MyCountDownTimer(60*1000,1000,get_sms_code)
        phone_num.addTextChangedListener(this)
        sms_code.addTextChangedListener(this)
    }
    fun login(v : View?){
        if(phone_num.text.length!=11){
            showToast("请输入正确的手机号")
            return
        }
        if(sms_code.text.isNullOrEmpty()){
            showToast("请输入验证码")
        }
        showLoading("登录中")
        viewModel.login(sms_code.text.toString(),token).observe(this, Observer {
            hideLoading()
            if (it.status == Resource.SUCCESS) {
                showToast("登录成功！")
                AccountRepository.getInstance(CoolWeatherNetwork.getInstance()).cacheUserInfo(it.data)
                newIntent<MainActivity>()
                finish()

            } else if (it.status == Resource.ERROR) {
                showToast("登录失败")
            }
        })
    }
    fun sendCode(v : View?) {
        if(phone_num.text.length!=11){
            showToast("请输入正确的手机号")
            return
        }
        showLoading("获取验证码")
        viewModel.getSmsCode(phone_num.text.toString()).observe(this, Observer {
            result ->
             token = result.data!!.token
            mCountDownTimer.start()
            hideLoading()
            showToast("验证码已经下发")
        })
        get_sms_code.isEnabled = false
    }

    fun checkUserAgreement( v: View?) {

    }

    fun gotoUserAgreement( v: View?) {

    }
    private inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long, internal var tv: Button) : CountDownTimer(millisInFuture,countDownInterval){
        override fun onFinish() {
            tv.isEnabled = true
            tv.text = "获取验证码"
        }

        override fun onTick(millisUntilFinished: Long) {
            tv.text = "${millisUntilFinished/1000}秒重新获取"
        }

    }
}
