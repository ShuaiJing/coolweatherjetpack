package com.dbwwt.mall.ui.login

import androidx.lifecycle.ViewModel
import com.dbwwt.mall.data.AccountRepository

class LoginViewModel (private var repository: AccountRepository) : ViewModel() {
    fun getSmsCode(phone:String) = repository.getSmsCode(phone)
    fun login(code:String,token:String) = repository.login(code,token)

}