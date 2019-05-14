package com.coolweather.coolweatherjetpack.ui.main

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coolweather.coolweatherjetpack.R
import com.coolweather.coolweatherjetpack.util.InjectorUtil
import com.coolweather.coolweatherjetpack.ui.weather.WeatherActivity
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.coolweather.coolweatherjetpack.adapter.MainPagerAdapter
import com.coolweather.coolweatherjetpack.ui.BaseActivity
import com.coolweather.coolweatherjetpack.ui.BaseFragment
import com.coolweather.coolweatherjetpack.ui.account.AccountFragment
import com.coolweather.coolweatherjetpack.ui.home.HomeFragment
import com.coolweather.coolweatherjetpack.ui.shoppingCart.ShoppingCartFragment
import com.coolweather.coolweatherjetpack.util.ScreenUtil
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var mHomeFragment : HomeFragment?=null
    private var mShoppingCartFragment : ShoppingCartFragment?=null
    private var mAccountFragment : AccountFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ScreenUtil.setStatusBarColor(this,ContextCompat.getColor(this,R.color.white))
//        if (KEY.isEmpty()) {
//            val builder = AlertDialog.Builder(this)
//            builder.setMessage("请先在MainActivity中配置天气API的Key")
//            builder.setCancelable(false)
//            builder.setPositiveButton("确定") { _, _ ->
//                finish()
//            }
//            builder.show()
//        } else {
//            val viewModel = ViewModelProviders.of(this, InjectorUtil.getMainModelFactory()).get(MainViewModel::class.java)
//            if (viewModel.isWeatherCached()) {
//                val intent = Intent(this, WeatherActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
        initFragment()
        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_SHOPPING_CART, R.drawable.ic_shopping_cart))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account))
        bottomNavigation.setOnClickMenuListener {
           showTab(it.id)
        }
        bottomNavigation.show(ID_HOME, true)

    }
    fun initFragment(){
        mHomeFragment = HomeFragment()
        mShoppingCartFragment = ShoppingCartFragment()
        mAccountFragment = AccountFragment()
        var mFragments = ArrayList<BaseFragment>()
        mFragments.add(mHomeFragment!!)
        mFragments.add(mShoppingCartFragment!!)
        mFragments.add(mAccountFragment!!)
        viewPager.adapter = MainPagerAdapter(this,mFragments)
    }

    fun showTab(id : Int){
        when(id) {
            ID_HOME -> {
                viewPager.setCurrentItem(ID_HOME,false)
            }
            ID_SHOPPING_CART -> {
                viewPager.setCurrentItem(ID_SHOPPING_CART,false)
            }

            ID_ACCOUNT -> {
                viewPager.setCurrentItem(ID_ACCOUNT,false)
            }
        }
    }
    companion object {
        // 备用Key，由于每个Key每天只有1000次免费请求，如果已用超的话请换别的Key使用。
        // 9da35b0a6b2c48498ed9e81b9d5206f3
        // 0099dcee07fd488e8b8866f16453fa2e
        const val KEY = "45dd25f63300445e967b461d2037e4f9"

        private const val ID_HOME = 0
        private const val ID_SHOPPING_CART = 1
        private const val ID_ACCOUNT = 2
    }

}
