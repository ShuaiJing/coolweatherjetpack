package com.dbwwt.mall.ui.goodsDetail

import androidx.lifecycle.ViewModel
import com.dbwwt.mall.data.GoodsRepository

class GoodsDetailViewModel constructor(private val repository: GoodsRepository): ViewModel() {
    fun getGoodsDetail(id: Int) = repository.getGoodsDetail(id)
}