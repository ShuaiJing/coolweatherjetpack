package com.coolweather.coolweatherjetpack.data.model.Account

data class LoginReq(
    val code: String,
    val token: String,
    val wx_token: String
)