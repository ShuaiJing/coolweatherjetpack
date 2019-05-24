package com.dbwwt.mall.ui.shoppingCart

import androidx.lifecycle.ViewModel
import com.dbwwt.mall.data.GoodsRepository
import java.util.*

class ShoppingCartViewModel constructor(private val repository : GoodsRepository): ViewModel() {
    fun getShoppingCartList() = repository.getShoppingCartList()
    var dataList = ArrayList<String>()
}