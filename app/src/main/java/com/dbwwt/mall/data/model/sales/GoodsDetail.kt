package com.dbwwt.mall.data.model.sales


import com.google.gson.annotations.SerializedName

data class GoodsDetail(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("status")
    var status: String
) {
    data class Data(
        @SerializedName("category")
        var category: String,
        @SerializedName("ctime")
        var ctime: String,
        @SerializedName("goodsId")
        var goodsId: Int,
        @SerializedName("image")
        var image: List<String>,
        @SerializedName("litimg")
        var litimg: String,
        @SerializedName("mtime")
        var mtime: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("otime")
        var otime: String,
        @SerializedName("remark")
        var remark: Any,
        @SerializedName("retailPrice")
        var retailPrice: Double,
        @SerializedName("vipPrice")
        var vipPrice: Double,
        @SerializedName("wtcDiscount")
        var wtcDiscount: Double,
        @SerializedName("wtcExpend")
        var wtcExpend: Double
    )
}