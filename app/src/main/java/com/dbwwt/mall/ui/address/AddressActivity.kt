package com.dbwwt.mall.ui.address

import android.os.Bundle
import com.dbwwt.mall.R
import com.dbwwt.mall.ui.BaseActivity
import com.dbwwt.mall.util.newIntent
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.content_toolbar.*

class AddressActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_address
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.title = "收货地址管理"
        addAddress.setOnClickListener { newIntent<AddAddressActivity>() }

    }
}
