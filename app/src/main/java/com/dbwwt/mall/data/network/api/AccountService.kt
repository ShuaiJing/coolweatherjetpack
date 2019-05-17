package com.dbwwt.mall.data.network.api

import com.dbwwt.mall.data.model.Account.LoginRes
import com.dbwwt.mall.data.model.Account.SmsRes
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccountService {

//    @GET("carousel")
//    fun getBanner(): Call<BannerRes>
//
//    @GET("goods/{categoryId}")
//    fun getGoods(@Path("categoryId" )id:Int):Call<List<Goods>>
   @Headers("Content-Type: application/json")
    @POST("login/sms_code")
    fun getSmsCode(@Body req : RequestBody) : Call<SmsRes>
    @Headers("Content-Type: application/json")
    @POST("login/mobile")
    fun login(@Body req:RequestBody): Call<LoginRes>
}