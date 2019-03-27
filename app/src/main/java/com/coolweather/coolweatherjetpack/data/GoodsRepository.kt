package com.coolweather.coolweatherjetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coolweather.coolweatherjetpack.data.network.CoolWeatherNetwork

class GoodsRepository private constructor(var network : CoolWeatherNetwork){
    companion object {
        private var instance : GoodsRepository?=null
        fun getInstance (network: CoolWeatherNetwork) : GoodsRepository {
            if (instance == null) {
                synchronized(GoodsRepository::class.java){
                    if(instance == null) {
                        instance = GoodsRepository(network)
                    }
                }
            }
            return instance!!
        }

    }

    fun getIndexGoods() : LiveData<Resource<ArrayList<String>>> {
        var liveData = MutableLiveData<Resource<ArrayList<String>>>()
        var list = arrayListOf<String>("https://img.alicdn.com/imgextra/i1/105046306/O1CN011wSC2u07vTl6p2A_!!105046306.jpg" ,
            "https://img.alicdn.com/imgextra/i1/105046306/O1CN011wSC2u07vTl6p2A_!!105046306.jpg" ,
            "https://img.alicdn.com/imgextra/i1/105046306/O1CN011wSC2u07vTl6p2A_!!105046306.jpg" ,
            "https://img.alicdn.com/imgextra/i3/105046306/O1CN011wSC2tinSE7Wpnb_!!105046306.jpg")
        liveData.postValue(Resource.success(list))
        return liveData!!
    }

    fun getShoppingCartList() : LiveData<Resource<ArrayList<String>>> {
        var liveData = MutableLiveData<Resource<ArrayList<String>>>()
        var list = arrayListOf<String>("商品1" ,
            "商品2" ,
            "商品3" ,
            "商品4")
        liveData.postValue(Resource.success(list))
        return liveData!!
    }
}