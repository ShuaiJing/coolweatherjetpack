package com.dbwwt.mall.data.network.api

import com.dbwwt.mall.data.model.sales.BannerRes
import com.dbwwt.mall.data.model.sales.Goods
import com.dbwwt.mall.data.model.sales.GoodsDetail
import com.dbwwt.mall.data.model.sales.ShoppingCartRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SalesService {

    @GET("carousel")
    fun getBanner(): Call<BannerRes>

    @GET("goods/{categoryId}")
    fun getGoods(@Path("categoryId" )id:Int):Call<List<Goods>>

//    @GET("api/china")
//    fun getProvinces(): Call<List<Province>>
//
//    @GET("api/china/{provinceId}")
//    fun getCities(@Path("provinceId") provinceId: Int): Call<List<City>>
//
//    @GET("api/china/{provinceId}/{cityId}")
//    fun getCounties(@Path("provinceId") provinceId: Int, @Path("cityId") cityId: Int): Call<List<County>>
    @GET("shoppingcart/get")
    fun getShoppingCart() : Call<List<ShoppingCartRes>>

    @GET("goods/get/{id}")
    fun getGoodsDetail(@Path("id")id: Int) : Call<GoodsDetail>

}