package com.dbwwt.mall.ui.main

import androidx.lifecycle.ViewModel
import com.dbwwt.mall.data.WeatherRepository

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    fun isWeatherCached() = repository.isWeatherCached()

}