package com.dbwwt.mall.ui.goodsDetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dbwwt.mall.R
import com.dbwwt.mall.data.Resource
import com.dbwwt.mall.ui.BaseActivity
import com.dbwwt.mall.util.GlideImageLoader
import com.dbwwt.mall.util.InjectorUtil
import kotlinx.android.synthetic.main.activity_scrolling.toolbar
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.fragment_home.*

class GoodsDetailActivity : BaseActivity() {
    private lateinit var viewModel: GoodsDetailViewModel
    override fun layoutId(): Int {
        return R.layout.activity_goods_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, InjectorUtil.getGoodsDetailModelFactory()).get(GoodsDetailViewModel::class.java)
        toolbar.setNavigationOnClickListener { finish() }
        loadData()
//        swipeRefresh.setOnClickListener { loadData() }
    }

    fun loadData() {
//        swipeRefresh.isRefreshing = true
        if(!intent.hasExtra(GOODS_ID)) {
            swipeRefresh.isRefreshing = false
            return
        }
        var id = intent.getIntExtra(GOODS_ID,-1)
        viewModel.getGoodsDetail(id).observe(this, Observer {
            if (it.status == Resource.SUCCESS) {
                var goods = it.data
                banner?.setImageLoader(GlideImageLoader())
                banner.setImages(goods!!.data.image)
                banner?.start()
                name.text = goods.data.name
                price.text = goods.data.retailPrice.toString()
                Glide.with(this).load(goods.data.litimg).into(desImg)
            }
//            swipeRefresh.isRefreshing = false
        })
    }
    companion object {
        val GOODS_ID = "goods_id"
        fun navigation(context:Activity,id:Int) {
            var intent = Intent(context,GoodsDetailActivity::class.java)
            intent.putExtra(GOODS_ID,id)
            context.startActivity(intent)
        }
    }
}
