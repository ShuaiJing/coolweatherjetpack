package com.dbwwt.mall.ui.account

import android.os.Bundle
import com.dbwwt.mall.R
import com.dbwwt.mall.ui.BaseActivity
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.settings_activity.*

class SettingsActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.settings_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.title = "设置"
        toolbar.setNavigationOnClickListener { finish() }
        address.setOnClickListener {  }
        wallet_address.setOnClickListener {  }
        about.setOnClickListener {  }
        logout.setOnClickListener{ }
    }


}