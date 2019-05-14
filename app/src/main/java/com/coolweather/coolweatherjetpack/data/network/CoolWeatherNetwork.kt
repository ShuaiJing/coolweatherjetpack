package com.coolweather.coolweatherjetpack.data.network

import com.coolweather.coolweatherjetpack.data.model.place.City
import com.coolweather.coolweatherjetpack.data.model.place.County
import com.coolweather.coolweatherjetpack.data.model.place.Province
import com.coolweather.coolweatherjetpack.data.model.sales.BannerRes
import com.coolweather.coolweatherjetpack.data.model.sales.Goods
import com.coolweather.coolweatherjetpack.data.model.weather.HeWeather
import com.coolweather.coolweatherjetpack.data.network.api.PlaceService
import com.coolweather.coolweatherjetpack.data.network.api.SalesService
import com.coolweather.coolweatherjetpack.data.network.api.WeatherService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Callback

class CoolWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    private val salesService = ServiceCreator.create(SalesService::class.java)
    fun fetchBanners(callback: Callback<BannerRes>) = salesService.getBanner().enqueue(callback)
    fun fetchGoods(categoryId: Int,callback: Callback<List<Goods>>) = salesService.getGoods(categoryId).enqueue(callback)
    fun fetchProvinceList(callback: Callback<List<Province>>) = placeService.getProvinces().enqueue(callback)

    fun fetchCityList(provinceId: Int, callback: Callback<List<City>>) = placeService.getCities(provinceId).enqueue(callback)

    fun fetchCountyList(provinceId: Int, cityId: Int, callback: Callback<List<County>>) = placeService.getCounties(provinceId, cityId).enqueue(callback)

    fun fetchWeather(weatherId: String, key: String, callback: Callback<HeWeather>) = weatherService.getWeather(weatherId, key).enqueue(callback)

    fun fetchBingPic(callback: Callback<String>) = weatherService.getBingPck().enqueue(callback)

    companion object {

        private var network: CoolWeatherNetwork? = null

        fun getInstance(): CoolWeatherNetwork {
            if (network == null) {
                synchronized(CoolWeatherNetwork::class.java) {
                    if (network == null) {
                        network = CoolWeatherNetwork()
                    }
                }
            }
            return network!!
        }

    }
//    fun getRequestBody(body :String) = RequestBody.create(MediaType.parse("application/json"),body)

}