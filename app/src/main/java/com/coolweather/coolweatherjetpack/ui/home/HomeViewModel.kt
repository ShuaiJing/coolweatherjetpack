package com.coolweather.coolweatherjetpack.ui.home

import androidx.lifecycle.ViewModel
import com.coolweather.coolweatherjetpack.data.GoodsRepository

class HomeViewModel (private var repository: GoodsRepository) : ViewModel() {
    fun getBanners() = repository.getBanners()
    fun getGoods(categoryId: Int) = repository.getGoods(categoryId)

}