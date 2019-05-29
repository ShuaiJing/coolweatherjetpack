package com.dbwwt.mall.data.network

import android.util.Log
import com.dbwwt.mall.data.AccountRepository
import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var build = chain.request().newBuilder()
        var token = AccountRepository.getInstance(CoolWeatherNetwork()).token
        if (token.isNullOrEmpty()) {
            token = ""
        }
        build.addHeader("Authorization", token)
        Log.d("token:", token)
        return chain.proceed(build.build())
    }
}