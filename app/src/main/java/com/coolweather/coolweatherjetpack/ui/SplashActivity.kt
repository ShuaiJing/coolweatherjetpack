package com.coolweather.coolweatherjetpack.ui

import android.os.Bundle
import android.view.WindowManager
import com.coolweather.coolweatherjetpack.R
import com.coolweather.coolweatherjetpack.ui.login.LoginActivity
import com.coolweather.coolweatherjetpack.ui.main.MainActivity
import com.coolweather.coolweatherjetpack.util.newIntent

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        window.decorView.postDelayed({ newIntent<MainActivity>();finish() }, 2000)

    }
}
