package com.coolweather.coolweatherjetpack.ui.shoppingCart

import androidx.lifecycle.ViewModel
import com.coolweather.coolweatherjetpack.data.GoodsRepository

class ShoppingCartViewModel constructor(private val repository : GoodsRepository): ViewModel() {
    fun getShoppingCartList() = repository.getShoppingCartList()
    var dataList = ArrayList<String>()
}