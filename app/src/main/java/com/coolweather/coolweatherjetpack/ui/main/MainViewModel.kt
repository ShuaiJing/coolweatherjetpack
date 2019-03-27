package com.coolweather.coolweatherjetpack.ui.main

import androidx.lifecycle.ViewModel
import com.coolweather.coolweatherjetpack.data.WeatherRepository

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    fun isWeatherCached() = repository.isWeatherCached()

}