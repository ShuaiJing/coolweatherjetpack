package com.dbwwt.mall.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.dbwwt.mall.R
import com.dbwwt.mall.ui.main.MainActivity
import com.dbwwt.mall.util.newIntent

class SplashActivity : BaseActivity() {
    override fun layoutId(): Int {

        return R.layout.activity_splash
    }

     override fun onCreate(savedInstanceState: Bundle?) {

         if (!this.isTaskRoot) {
             val mainIntent = intent
             val action = mainIntent.action
             if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action == Intent.ACTION_MAIN) {
                 finish()
                 return
             }
         }
         super.onCreate(savedInstanceState)

         window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.postDelayed({ newIntent<MainActivity>();finish() }, 2000)

    }
}
