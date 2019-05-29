package com.dbwwt.mall.data

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dbwwt.mall.CoolWeatherApplication
import com.dbwwt.mall.data.model.Account.*
import com.dbwwt.mall.data.network.CoolWeatherNetwork
import com.dbwwt.mall.util.CoolWeatherExecutors
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository private constructor(var network: CoolWeatherNetwork){
    val  USER_INFO = "user_info"
    var token = ""
    var userInfo :UserInfo?=getCachedUserInfo()
    var isLogined = false
    companion object {
        private var instance : AccountRepository?=null
        fun getInstance (network: CoolWeatherNetwork) : AccountRepository {
            if (instance == null) {
                synchronized(AccountRepository::class.java){
                    if(instance == null) {
                        instance = AccountRepository(network)

                    }
                }
            }
            return instance!!
        }

    }
    fun getCachedUserInfo(): UserInfo? {
        val userInfo = PreferenceManager.getDefaultSharedPreferences(CoolWeatherApplication.context).getString(USER_INFO, null)
        if (userInfo != null) {

            Log.d("userinfo",userInfo)
            var response = Gson().fromJson(userInfo, LoginRes::class.java)
            token = response.user_token
            if (!token.isNullOrEmpty()) isLogined = true

            return response.user_info
        }
        return null
    }
    fun cacheUserInfo(user: LoginRes?) {
        if (user == null) return
        PreferenceManager.getDefaultSharedPreferences(CoolWeatherApplication.context).edit {
            val userInfo = Gson().toJson(user)
            putString(USER_INFO, userInfo)
        }
        isLogined = true
        userInfo = user.user_info
    }

    fun getSmsCode(phone:String) : LiveData<Resource<SmsRes>> {
        var liveData = MutableLiveData<Resource<SmsRes>>()
        CoolWeatherExecutors.networkIO.execute{
            var smsReq = SmsReq(phone,"86")
//            smsReq.mobile = phone
//            smsReq.nation_code = "86"
            var requestBody = RequestBody.create(MediaType.parse("application/json"),Gson().toJson(smsReq))
            network.fetchSmsCode(requestBody,object : Callback<SmsRes> {
                override fun onFailure(call: Call<SmsRes>, t: Throwable) {
                    Log.d("aaaa","sms error")
                    liveData.postValue(Resource.error("获取验证码失败", null))

                }

                override fun onResponse(call: Call<SmsRes>, response: Response<SmsRes>) {
                    Log.d("aaaa","sms success")
                    var result = response.body()
                    liveData.postValue(Resource.success(result))
                }
            })

        }
        return liveData
    }
    fun login( code:String,token:String) : LiveData<Resource<LoginRes>> {
        var liveData = MutableLiveData<Resource<LoginRes>>()
        CoolWeatherExecutors.networkIO.execute{
            var loginReq = LoginReq(code,token,"")
            var requestBody = RequestBody.create(MediaType.parse("application/json"),Gson().toJson(loginReq))
            network.fetchLogin(requestBody,object : Callback<LoginRes> {
                override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                    liveData.postValue(Resource.error("登录失败", null))
                }

                override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                    var result = response.body()
                    liveData.postValue(Resource.success(result))
                }
            })
        }
        return liveData
    }

    private fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        action(editor)
        editor.apply()
    }

    fun logout() {
        PreferenceManager.getDefaultSharedPreferences(CoolWeatherApplication.context).edit().clear().commit()
        isLogined = false
    }
}