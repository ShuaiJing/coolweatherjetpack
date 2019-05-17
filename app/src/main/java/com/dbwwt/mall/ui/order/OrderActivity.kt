package com.dbwwt.mall.ui.order

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dbwwt.mall.CoolWeatherApplication.Companion.context
import com.dbwwt.mall.R
import com.dbwwt.mall.adapter.EmptyAdapter
import com.dbwwt.mall.ui.BaseActivity
import com.dbwwt.mall.ui.common.CommonPagerAdapter
import com.dbwwt.mall.ui.common.DividerItemDecoration
import com.dbwwt.mall.util.InjectorUtil
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.fragment_home.tablayout

class OrderActivity : BaseActivity() {
    private var titles = arrayOf("全部","待付款","待发货","已发货","退换货" )
    private var recyclerViews = ArrayList<RecyclerView>()
    override fun layoutId(): Int {
        return R.layout.activity_order
    }
    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, InjectorUtil.getOrderModelFactory()).get(OrderViewModel::class.java)
        for (i in 1..5) {
            var recyclerView = initRecyclerView()
            recyclerView.adapter = EmptyAdapter(this)
            recyclerViews.add(recyclerView)
        }
        viewPager.adapter = CommonPagerAdapter(recyclerViews, titles)
        tablayout.setupWithViewPager(viewPager)
        toolbar.title = "我的订单"
        toolbar.setNavigationOnClickListener { finish() }
    }
    fun initRecyclerView() : RecyclerView{
        var recyclerView = RecyclerView(context!!)
        recyclerView.layoutParams = ViewPager.LayoutParams()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.HORIZONTAL_LIST,20, Color.WHITE))
        return recyclerView
    }
    companion object {

        fun navigation(activity: Activity,index : Int) {
            var intent = Intent(activity,OrderActivity::class.java)
            intent.putExtra(INDEX,index)
            activity.startActivity(intent)
        }
        fun navigation(activity: FragmentActivity,index : Int) {
            var intent = Intent(activity,OrderActivity::class.java)
            intent.putExtra(INDEX,index)
            activity.startActivity(intent)
        }
        private const val INDEX = "index"
    }
}
