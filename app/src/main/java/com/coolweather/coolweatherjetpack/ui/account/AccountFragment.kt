package com.coolweather.coolweatherjetpack.ui.account

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.coolweather.coolweatherjetpack.R
import com.coolweather.coolweatherjetpack.ui.BaseFragment
import com.coolweather.coolweatherjetpack.ui.common.CommonAdapter
import com.coolweather.coolweatherjetpack.ui.common.DividerItemDecoration
import com.coolweather.coolweatherjetpack.ui.common.DividerItemDecoration.Companion.BOTH_SET
import com.coolweather.coolweatherjetpack.ui.common.ViewHolder
import com.coolweather.coolweatherjetpack.ui.login.LoginActivity
import com.coolweather.coolweatherjetpack.util.newIntent
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseFragment() {
    private lateinit var adapter : CommonAdapter<String>
    private lateinit var lists : ArrayList<String>
    override fun getLayoutResources(): Int {
        return R.layout.fragment_account
    }

    override fun initView(view: View) {
        login?.setOnClickListener{
            activity?.newIntent<LoginActivity>()
        }
        lists = arrayListOf("地址","等等","地址","等等","地址","等等","地址","等等","地址","等等","地址","等等")
        recyclerView.layoutManager = GridLayoutManager(context,3)
        adapter = object :CommonAdapter<String>(context!!,R.layout.item_account_page,lists) {
            override fun convert(holder: ViewHolder, t: String) {
                holder.setText(R.id.txtDes,t)
            }

        }
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context!!,BOTH_SET,1, Color.LTGRAY))
    }
}