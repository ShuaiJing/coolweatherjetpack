package com.dbwwt.mall.ui.shoppingCart

import android.graphics.Color
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbwwt.mall.R
import com.dbwwt.mall.ui.BaseFragment
import com.dbwwt.mall.ui.common.CommonAdapter
import com.dbwwt.mall.ui.common.DividerItemDecoration
import com.dbwwt.mall.ui.common.ViewHolder
import com.dbwwt.mall.util.InjectorUtil
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCartFragment : BaseFragment() {
    lateinit var viewModel: ShoppingCartViewModel
    lateinit var adapter : CommonAdapter<String>
    override fun initView(view: View) {
        toolbar.navigationIcon = null
        toolbar.title = "购物车"
        viewModel = ViewModelProviders.of(this,InjectorUtil.getShoppingCartFactory()).get(ShoppingCartViewModel::class.java)
        adapter = object : CommonAdapter<String>(context!!,R.layout.item_cart ,viewModel.dataList){
            override fun convert(holder: ViewHolder, t: String) {
                holder.setText(android.R.id.title, t)
            }

        }
        viewModel.getShoppingCartList().observe(this, Observer {
            result->
            var list = result.data
            viewModel.dataList.addAll(list!!)

        })
        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.HORIZONTAL_LIST,5, Color.LTGRAY))
        recyclerView.adapter = adapter
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_shopping_cart
    }
}