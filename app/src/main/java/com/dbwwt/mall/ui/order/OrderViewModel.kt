package com.dbwwt.mall.ui.order

import androidx.lifecycle.ViewModel
import com.dbwwt.mall.data.GoodsRepository

class OrderViewModel(private val repository: GoodsRepository) :ViewModel(){
    fun getOrderList() = repository.getIndexGoods()
}