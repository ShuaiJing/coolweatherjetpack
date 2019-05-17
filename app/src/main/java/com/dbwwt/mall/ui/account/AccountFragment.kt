package com.dbwwt.mall.ui.account

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.dbwwt.mall.R
import com.dbwwt.mall.data.AccountRepository
import com.dbwwt.mall.data.network.CoolWeatherNetwork
import com.dbwwt.mall.ui.BaseFragment
import com.dbwwt.mall.ui.common.CommonAdapter
import com.dbwwt.mall.ui.login.LoginActivity
import com.dbwwt.mall.ui.main.MainActivity
import com.dbwwt.mall.ui.order.OrderActivity
import com.dbwwt.mall.util.InjectorUtil
import com.dbwwt.mall.util.newIntent
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseFragment() {
    private lateinit var viewModel: AccountViewModel
    private lateinit var adapter : CommonAdapter<String>
    private lateinit var lists : ArrayList<String>
    override fun getLayoutResources(): Int {
        return R.layout.fragment_account
    }

    override fun initView(view: View) {
        login?.setOnClickListener{
            activity?.newIntent<LoginActivity>()
        }

//        lists = arrayListOf("地址","等等","地址","等等","地址","等等","地址","等等","地址","等等","地址","等等")
//        recyclerView.layoutManager = GridLayoutManager(context,3)
//        adapter = object :CommonAdapter<String>(context!!,R.layout.item_account_page,lists) {
//            override fun convert(holder: ViewHolder, t: String) {
//                holder.setText(R.id.txtDes,t)
//            }
//
//        }
//        recyclerView.adapter = adapter
//        recyclerView.addItemDecoration(DividerItemDecoration(context!!,BOTH_SET,1, Color.LTGRAY))
        viewModel = ViewModelProviders.of(this, InjectorUtil.getAccountModelFactory()).get(AccountViewModel::class.java)
        if (AccountRepository.getInstance(CoolWeatherNetwork.getInstance()).isLogined){
            login.isClickable = false
            login.text = InjectorUtil.getAccountRepository().userInfo!!.mobile
        }
        order.setOnClickListener { OrderActivity.navigation(activity!!,0) }
        unpay.setOnClickListener { OrderActivity.navigation(activity!!,1) }
        unpost.setOnClickListener { OrderActivity.navigation(activity!!,2) }
        post.setOnClickListener { OrderActivity.navigation(activity!!,3) }
        after_sale.setOnClickListener { OrderActivity.navigation(activity!!,4) }
        setting.setOnClickListener {
            startActivity(Intent(activity,SettingsActivity::class.java))
        }

    }
}