package com.dbwwt.mall.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dbwwt.mall.data.model.sales.BannerRes
import com.dbwwt.mall.data.model.sales.Goods
import com.dbwwt.mall.data.model.sales.GoodsDetail
import com.dbwwt.mall.data.model.sales.ShoppingCartRes
import com.dbwwt.mall.data.network.CoolWeatherNetwork
import com.dbwwt.mall.util.CoolWeatherExecutors
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
    fun getGoodsDetail(id:Int) : LiveData<Resource<GoodsDetail>> {
        var liveData = MutableLiveData<Resource<GoodsDetail>>()
        CoolWeatherExecutors.networkIO.execute{
            network.fetchGoodsDetail(id, object : Callback<GoodsDetail> {
                override fun onFailure(call: Call<GoodsDetail>, t: Throwable) {
                    liveData.postValue(Resource.error("获取商品失败", null)) //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<GoodsDetail>, response: Response<GoodsDetail>) {
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

    fun getShoppingCartList() : LiveData<Resource<List<ShoppingCartRes>>> {
        var liveData = MutableLiveData<Resource<List<ShoppingCartRes>>>()
        CoolWeatherExecutors.networkIO.execute{
            network.fetchShoppingCart(object :Callback<List<ShoppingCartRes>>{
                override fun onFailure(call: Call<List<ShoppingCartRes>>, t: Throwable) {
                    liveData.postValue(Resource.error("获取购物失败", null))
                }

                override fun onResponse(call: Call<List<ShoppingCartRes>>, response: Response<List<ShoppingCartRes>>) {
                    var result = response.body()
                    liveData.postValue(Resource.success(result))
                }

            })
        }
        return liveData
    }
}