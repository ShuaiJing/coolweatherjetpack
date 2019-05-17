package com.dbwwt.mall.ui.goodsDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dbwwt.mall.data.GoodsRepository

class GoodsDetailModelFactory (private val repository: GoodsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GoodsDetailViewModel(repository) as T
    }
}