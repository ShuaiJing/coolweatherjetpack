package com.dbwwt.mall.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager
import androidx.annotation.ColorInt

object ScreenUtil {

    /**
     * 获取屏幕实际的宽高，包括底部导航栏(若存在)
     *
     * @return Point.x为屏幕宽度，Point.y为屏幕高度
     */
    fun getScreenRealSize(context: Context): Point {
        val point = Point()
        val outMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            windowManager.defaultDisplay.getRealMetrics(outMetrics)
        } else {
            outMetrics.heightPixels = windowManager.defaultDisplay.height
            outMetrics.widthPixels = windowManager.defaultDisplay.width
        }
        point.x = outMetrics.widthPixels
        point.y = outMetrics.heightPixels
        return point
    }

    /**
     * 获取状态栏的高度
     */
    fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 获取底部导航栏的高度
     */
    fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 判断是否存在底部导航栏
     */
    fun hasNavigationBar(context: Context): Boolean {
        val resources = context.resources
        val identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        if (identifier != 0) {
            var hasNavigationBar = resources.getBoolean(identifier)

            //获取底部导航栏被重写的状态
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                try {
                    val c = Class.forName("android.os.SystemProperties")
                    val method = c.getDeclaredMethod("get", String::class.java)
                    method.isAccessible = true
                    val overrideStatus = method.invoke(null, "qemu.hw.mainkeys") as String
                    if ("1" == overrideStatus) {//注意：不能写成“if...else...”判断形式
                        hasNavigationBar = false
                    } else if ("0" == overrideStatus) {
                        hasNavigationBar = true
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            return hasNavigationBar
        } else {
            return !ViewConfiguration.get(context).hasPermanentMenuKey()
        }
    }

//    fun dip2px(dipValue: Float): Int {
//        return Math.round(RuntimeContext.getApplication().getResources().getDisplayMetrics().density * dipValue)
//    }

    fun getThemeColor(context: Context, attributeColor: Int): Int {
        val typedValue = TypedValue()
        val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(attributeColor))
        val color = a.getColor(0, 0)
        a.recycle()
        return color
    }

    fun setStatusBarColor(activity: Activity, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color

            //设置状态栏前景颜色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val view = window.decorView
                var flags = view.systemUiVisibility
                if (color == Color.WHITE) {
                    flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
                view.systemUiVisibility = flags
            }
        }
    }
}