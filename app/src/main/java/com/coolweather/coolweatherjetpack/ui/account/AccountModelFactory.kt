package com.coolweather.coolweatherjetpack.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coolweather.coolweatherjetpack.data.AccountRepository

class AccountModelFactory (private val repository : AccountRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountViewModel(repository) as T
    }
}