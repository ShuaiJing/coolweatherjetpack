package com.dbwwt.mall.data.model.sales

import com.dbwwt.mall.data.model.BaseResModel
import com.google.gson.annotations.SerializedName

class BannerRes : BaseResModel() {
    @SerializedName("list")
    lateinit var banners : List<BannerModel>
    inner class BannerModel{
        var id = ""
        var position = 1
        var goodsId = 1
        var image = ""
    }
   // {"id":10,"position":2,"goodsId":8,"image":"1545568031067.JPG"}
}