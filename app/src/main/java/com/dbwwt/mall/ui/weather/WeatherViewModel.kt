package com.dbwwt.mall.ui.weather

import androidx.lifecycle.ViewModel
import com.dbwwt.mall.data.WeatherRepository
import com.dbwwt.mall.data.model.weather.Weather

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    var weather: Weather? = null

    var bingPicUrl: String? = null

    fun getWeather(weatherId: String, key: String) = repository.getWeather(weatherId, key)

    fun refreshWeather(weatherId: String, key: String) = repository.refreshWeather(weatherId, key)

    fun isWeatherCached() = repository.isWeatherCached()

    fun getCachedWeather() = repository.getCachedWeather()

    fun getBingPic() = repository.getBingPic()

}