package com.coolweather.coolweatherjetpack.ui.goodsDetail

import android.os.Bundle
import com.coolweather.coolweatherjetpack.R
import com.coolweather.coolweatherjetpack.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_scrolling.*

class GoodsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        toolbar.setNavigationOnClickListener { finish() }
    }
}
