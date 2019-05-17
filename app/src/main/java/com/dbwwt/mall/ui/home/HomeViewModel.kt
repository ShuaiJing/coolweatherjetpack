package com.dbwwt.mall.ui.home

import androidx.lifecycle.ViewModel
import com.dbwwt.mall.data.GoodsRepository

class HomeViewModel (private var repository: GoodsRepository) : ViewModel() {
    fun getBanners() = repository.getBanners()
    fun getGoods(categoryId: Int) = repository.getGoods(categoryId)

}