package com.coolweather.coolweatherjetpack.ui.home

import android.graphics.Color
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coolweather.coolweatherjetpack.R
import com.coolweather.coolweatherjetpack.ui.BaseFragment
import com.coolweather.coolweatherjetpack.ui.common.CommonAdapter
import com.coolweather.coolweatherjetpack.ui.common.DividerItemDecoration
import com.coolweather.coolweatherjetpack.ui.common.DividerItemDecoration.Companion.BOTH_SET
import com.coolweather.coolweatherjetpack.ui.common.ViewHolder
import com.coolweather.coolweatherjetpack.util.GlideImageLoader
import com.coolweather.coolweatherjetpack.util.InjectorUtil
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel
    override fun getLayoutResources(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
        var images = listOf("https://img.alicdn.com/imgextra/i4/2782969320/O1CN012Iibo168uHP1cQf_!!2782969320.jpg", "https://img.alicdn.com/imgextra/i3/120976213/TB2UslAGHSYBuNjSspiXXXNzpXa_!!120976213.jpg")
        banner?.setImageLoader(GlideImageLoader())
        banner?.setImages(images)
        banner?.start()
        toolbar?.navigationIcon = null
        toolbar?.title = "主页"
        viewModel = ViewModelProviders.of(this,InjectorUtil.getHomeModelFactory()).get(HomeViewModel::class.java)
//        recyclerView.adapter = CommonAdapter<String>(context!!,R.layout.item_goods,viewModel.dataList)

        viewModel.getIndexGoods().observe(this, Observer {
            result ->
            var list = result.data
            viewModel.dataList.addAll(list!!)
        })
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.addItemDecoration(DividerItemDecoration(context!!,BOTH_SET,20, Color.WHITE))
        recyclerView.adapter = object : CommonAdapter<String>(context!!,R.layout.item_goods,viewModel.dataList) {
           override fun convert(holder: ViewHolder, t: String) {

           }

       }

    }
}