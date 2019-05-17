package com.dbwwt.mall

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import cat.ereza.customactivityoncrash.activity.DefaultErrorActivity
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.dbwwt.mall.ui.SplashActivity
import org.litepal.LitePal

class CoolWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        context = this
        //整个配置属性，可以设置一个或多个，也可以一个都不设置
        CaocConfig.Builder.create()
            //程序在后台时，发生崩溃的三种处理方式
            //BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM: //当应用程序处于后台时崩溃，也会启动错误页面，
            //BackgroundMode.BACKGROUND_MODE_CRASH:      //当应用程序处于后台崩溃时显示默认系统错误（一个系统提示的错误对话框），
            //BackgroundMode.BACKGROUND_MODE_SILENT:     //当应用程序处于后台时崩溃，默默地关闭程序！
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM)
            .enabled(true)     //这阻止了对崩溃的拦截,false表示阻止。用它来禁用customactivityoncrash框架
            .showErrorDetails(true) //这将隐藏错误活动中的“错误详细信息”按钮，从而隐藏堆栈跟踪。
            .showRestartButton(true)    //是否可以重启页面
            .trackActivities(true)     //错误页面中显示错误详细信息
            .minTimeBetweenCrashesMs(2000)      //定义应用程序崩溃之间的最短时间，以确定我们不在崩溃循环中。比如：在规定的时间内再次崩溃，框架将不处理，让系统处理！
            .errorDrawable(applicationInfo.icon)     //崩溃页面显示的图标
            .restartActivity(SplashActivity::class.java)      //重新启动后的页面
            .errorActivity(DefaultErrorActivity::class.java) //程序崩溃后显示的页面
            .apply()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}