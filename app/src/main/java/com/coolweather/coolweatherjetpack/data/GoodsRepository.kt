package com.coolweather.coolweatherjetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coolweather.coolweatherjetpack.data.model.sales.BannerRes
import com.coolweather.coolweatherjetpack.data.model.sales.Goods
import com.coolweather.coolweatherjetpack.data.network.CoolWeatherNetwork
import com.coolweather.coolweatherjetpack.util.CoolWeatherExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    fun getBanners() : LiveData<Resource<BannerRes>> {
        var liveData = MutableLiveData<Resource<BannerRes>>()
        CoolWeatherExecutors.networkIO.execute{
            network.fetchBanners(object : Callback<BannerRes> {
                override fun onFailure(call: Call<BannerRes>, t: Throwable) {
                    liveData.postValue(Resource.error("获取商品失败", null))
                }

                override fun onResponse(call: Call<BannerRes>, response: Response<BannerRes>) {
                    var result = response.body()
                    liveData.postValue(Resource.success(result))
                }
            })
        }
        return liveData
    }

    fun getGoods(categoryId: Int) : LiveData<Resource<List<Goods>>> {
        var liveData = MutableLiveData<Resource<List<Goods>>>()
        CoolWeatherExecutors.networkIO.execute{
            network.fetchGoods(categoryId, object :Callback<List<Goods>>{
                override fun onFailure(call: Call<List<Goods>>, t: Throwable) {
                    liveData.postValue(Resource.error("获取商品失败", null))
                }

                override fun onResponse(call: Call<List<Goods>>, response: Response<List<Goods>>) {
                    var result = response.body()
                    liveData.postValue(Resource.success(result))
                }

            })
        }
        return liveData
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