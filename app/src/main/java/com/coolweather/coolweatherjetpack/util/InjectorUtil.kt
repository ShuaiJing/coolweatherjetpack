package com.coolweather.coolweatherjetpack.util

import com.coolweather.coolweatherjetpack.data.AccountRepository
import com.coolweather.coolweatherjetpack.data.GoodsRepository
import com.coolweather.coolweatherjetpack.data.PlaceRepository
import com.coolweather.coolweatherjetpack.data.WeatherRepository
import com.coolweather.coolweatherjetpack.data.db.CoolWeatherDatabase
import com.coolweather.coolweatherjetpack.data.network.CoolWeatherNetwork
import com.coolweather.coolweatherjetpack.ui.main.MainModelFactory
import com.coolweather.coolweatherjetpack.ui.area.ChooseAreaModelFactory
import com.coolweather.coolweatherjetpack.ui.goodsDetail.GoodsDetailModelFactory
import com.coolweather.coolweatherjetpack.ui.home.HomeModelFactory
import com.coolweather.coolweatherjetpack.ui.shoppingCart.ShoppingCartModelFactory
import com.coolweather.coolweatherjetpack.ui.weather.WeatherModelFactory

object InjectorUtil {

    private fun getPlaceRepository() = PlaceRepository.getInstance(CoolWeatherDatabase.getPlaceDao(), CoolWeatherNetwork.getInstance())

    private fun getWeatherRepository() = WeatherRepository.getInstance(CoolWeatherDatabase.getWeatherDao(), CoolWeatherNetwork.getInstance())
    private fun getGoodsRepository() = GoodsRepository.getInstance(CoolWeatherNetwork.getInstance())
    private fun getAccountRepository() = AccountRepository.getInstance(CoolWeatherNetwork.getInstance())
    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())

    fun getHomeModelFactory() = HomeModelFactory(getGoodsRepository())

    fun getGoodsDetailModelFactory() = GoodsDetailModelFactory(getGoodsRepository())

    fun getShoppingCartFactory() = ShoppingCartModelFactory(getGoodsRepository())
}