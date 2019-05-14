package com.coolweather.coolweatherjetpack.data.network.api

import com.coolweather.coolweatherjetpack.data.model.place.City
import com.coolweather.coolweatherjetpack.data.model.place.County
import com.coolweather.coolweatherjetpack.data.model.place.Province
import com.coolweather.coolweatherjetpack.data.model.sales.BannerRes
import com.coolweather.coolweatherjetpack.data.model.sales.Goods
import retrofit2.Call
import retrofit2.http.GET
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

}