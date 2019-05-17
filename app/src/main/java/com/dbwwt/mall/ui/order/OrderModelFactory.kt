package com.dbwwt.mall.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dbwwt.mall.data.GoodsRepository

class OrderModelFactory (private val repository : GoodsRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrderViewModel(repository) as T
    }
}