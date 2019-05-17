package com.dbwwt.mall.data.network.api

import com.dbwwt.mall.data.model.weather.HeWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("api/weather")
    fun getWeather(@Query("cityid") weatherId: String, @Query("key") key: String): Call<HeWeather>

    @GET("api/bing_pic")
    fun getBingPck(): Call<String>

}