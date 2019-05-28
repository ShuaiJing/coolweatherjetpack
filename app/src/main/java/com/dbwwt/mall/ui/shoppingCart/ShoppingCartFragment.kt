package com.dbwwt.mall.ui.shoppingCart

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbwwt.mall.R
import com.dbwwt.mall.adapter.EmptyAdapter
import com.dbwwt.mall.data.Resource
import com.dbwwt.mall.data.model.sales.Goods
import com.dbwwt.mall.data.model.sales.ShoppingCartRes
import com.dbwwt.mall.ui.BaseFragment
import com.dbwwt.mall.ui.common.CommonAdapter
import com.dbwwt.mall.ui.common.DividerItemDecoration
import com.dbwwt.mall.ui.common.ViewHolder
import com.dbwwt.mall.util.InjectorUtil
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCartFragment : BaseFragment() {
    lateinit var viewModel: ShoppingCartViewModel
    lateinit var adapter : CommonAdapter<ShoppingCartRes>
    override fun initView(view: View) {
        toolbar.navigationIcon = null
        toolbar.title = "购物车"
        viewModel = ViewModelProviders.of(this,InjectorUtil.getShoppingCartFactory()).get(ShoppingCartViewModel::class.java)
        adapter = object : CommonAdapter<ShoppingCartRes>(context!!,R.layout.item_cart ,viewModel.dataList){
            override fun convert(holder: ViewHolder, t: ShoppingCartRes) {
                holder.setText(android.R.id.title, t.name)
            }

        }
        swipeRefresh.isRefreshing = true
        swipeRefresh.setOnRefreshListener {
            loadData()
        }
        loadData()
        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.HORIZONTAL_LIST,5, Color.LTGRAY))
        recyclerView.adapter = adapter
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_shopping_cart
    }
    fun loadData() {
        viewModel.getShoppingCartList().observe(this, Observer {
                result->
            if (result.status == Resource.SUCCESS) {
                var list = result.data
                if (list != null)
                    viewModel.dataList.addAll(list!!)
            } else {
                recyclerView.adapter = EmptyAdapter(context!!)
                Toast.makeText(context,result.message,Toast.LENGTH_SHORT).show()
            }
            swipeRefresh.isRefreshing = false

        })
    }
}