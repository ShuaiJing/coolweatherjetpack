package com.coolweather.coolweatherjetpack.ui.shoppingCart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coolweather.coolweatherjetpack.data.GoodsRepository

class ShoppingCartModelFactory constructor(private val repository : GoodsRepository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingCartViewModel(repository) as T
    }
}