package com.dbwwt.mall.ui.goodsDetail

import android.os.Bundle
import com.dbwwt.mall.R
import com.dbwwt.mall.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_scrolling.*

class GoodsDetailActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_goods_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.setNavigationOnClickListener { finish() }
    }
}
