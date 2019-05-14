package com.coolweather.coolweatherjetpack.data

import com.coolweather.coolweatherjetpack.data.network.CoolWeatherNetwork

class AccountRepository private constructor(var network: CoolWeatherNetwork){
    companion object {
        private var instance : AccountRepository?=null
        fun getInstance (network: CoolWeatherNetwork) : AccountRepository {
            if (instance == null) {
                synchronized(AccountRepository::class.java){
                    if(instance == null) {
                        instance = AccountRepository(network)
                    }
                }
            }
            return instance!!
        }

    }

}