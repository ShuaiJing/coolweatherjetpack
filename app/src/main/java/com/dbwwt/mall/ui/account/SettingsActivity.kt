package com.dbwwt.mall.ui.account

import android.os.Bundle
import com.dbwwt.mall.R
import com.dbwwt.mall.data.AccountRepository
import com.dbwwt.mall.data.network.CoolWeatherNetwork
import com.dbwwt.mall.ui.BaseActivity
import com.dbwwt.mall.ui.address.AddressActivity
import com.dbwwt.mall.ui.main.MainActivity
import com.dbwwt.mall.util.newIntent
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
        address.setOnClickListener { newIntent<AddressActivity>() }
        wallet_address.setOnClickListener {  }
        about.setOnClickListener {  }
        logout.setOnClickListener{
            AccountRepository.getInstance(CoolWeatherNetwork()).logout()
            newIntent<MainActivity>()
            finish()
        }
    }


}