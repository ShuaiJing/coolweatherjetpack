package com.dbwwt.mall.data.model.Account

data class LoginRes(
    val status: String,
    val user_id: String,
    val user_info: UserInfo,
    val user_token: String
)