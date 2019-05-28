package com.dbwwt.mall.data.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCallback<T> : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {

    }

    override fun onResponse(call: Call<T>, response: Response<T>) {

    }
}