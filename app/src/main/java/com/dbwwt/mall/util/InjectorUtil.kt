package com.dbwwt.mall.util

import com.dbwwt.mall.data.AccountRepository
import com.dbwwt.mall.data.GoodsRepository
import com.dbwwt.mall.data.PlaceRepository
import com.dbwwt.mall.data.WeatherRepository
import com.dbwwt.mall.data.db.CoolWeatherDatabase
import com.dbwwt.mall.data.network.CoolWeatherNetwork
import com.dbwwt.mall.ui.account.AccountModelFactory
import com.dbwwt.mall.ui.main.MainModelFactory
import com.dbwwt.mall.ui.area.ChooseAreaModelFactory
import com.dbwwt.mall.ui.goodsDetail.GoodsDetailModelFactory
import com.dbwwt.mall.ui.home.HomeModelFactory
import com.dbwwt.mall.ui.login.LoginModelFactory
import com.dbwwt.mall.ui.order.OrderModelFactory
import com.dbwwt.mall.ui.shoppingCart.ShoppingCartModelFactory
import com.dbwwt.mall.ui.weather.WeatherModelFactory

object InjectorUtil {

    private fun getPlaceRepository() = PlaceRepository.getInstance(CoolWeatherDatabase.getPlaceDao(), CoolWeatherNetwork.getInstance())

    private fun getWeatherRepository() = WeatherRepository.getInstance(CoolWeatherDatabase.getWeatherDao(), CoolWeatherNetwork.getInstance())
    private fun getGoodsRepository() = GoodsRepository.getInstance(CoolWeatherNetwork.getInstance())
    fun getAccountRepository() = AccountRepository.getInstance(CoolWeatherNetwork.getInstance())
    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())

    fun getHomeModelFactory() = HomeModelFactory(getGoodsRepository())

    fun getLoginModelFactory() = LoginModelFactory(getAccountRepository())

    fun getAccountModelFactory() = AccountModelFactory(getAccountRepository())

    fun getGoodsDetailModelFactory() = GoodsDetailModelFactory(getGoodsRepository())

    fun getShoppingCartFactory() = ShoppingCartModelFactory(getGoodsRepository())

    fun getOrderModelFactory() = OrderModelFactory(getGoodsRepository())
}