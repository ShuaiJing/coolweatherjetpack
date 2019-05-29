package com.dbwwt.mall.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {
    const val TAG = "ServiceCreator"
    const val BASE_URL = "https://api.dbwwt.com/"
    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor()
    private val loginInterceptor = LoginInterceptor()
    //显示日志级别
    val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
    val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor.setLevel(level))
        .addInterceptor(loginInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)

    //okHttpClientBuilder


    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient.build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())


    private val retrofit = builder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}