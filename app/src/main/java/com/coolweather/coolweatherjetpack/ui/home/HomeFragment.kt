package com.coolweather.coolweatherjetpack.ui.home

import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.coolweather.coolweatherjetpack.R
import com.coolweather.coolweatherjetpack.data.model.sales.BannerRes
import com.coolweather.coolweatherjetpack.data.model.sales.Goods
import com.coolweather.coolweatherjetpack.data.network.CoolWeatherNetwork
import com.coolweather.coolweatherjetpack.data.network.ServiceCreator
import com.coolweather.coolweatherjetpack.ui.BaseFragment
import com.coolweather.coolweatherjetpack.ui.common.CommonAdapter
import com.coolweather.coolweatherjetpack.ui.common.CommonPagerAdapter
import com.coolweather.coolweatherjetpack.ui.common.DividerItemDecoration
import com.coolweather.coolweatherjetpack.ui.common.DividerItemDecoration.Companion.BOTH_SET
import com.coolweather.coolweatherjetpack.ui.common.ViewHolder
import com.coolweather.coolweatherjetpack.ui.goodsDetail.GoodsDetailActivity
import com.coolweather.coolweatherjetpack.util.GlideImageLoader
import com.coolweather.coolweatherjetpack.util.InjectorUtil
import com.coolweather.coolweatherjetpack.util.newIntent
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() ,
    ViewPager.OnPageChangeListener,
    AppBarLayout.OnOffsetChangedListener,
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: HomeViewModel
    private lateinit var oilRecyclerView :RecyclerView
    private lateinit var saltRecyclerView : RecyclerView
    private lateinit var suitRecyclerView : RecyclerView
    private  var banners  = ArrayList<BannerRes.BannerModel>()
    private var oilList = ArrayList<Goods>()
    private var saltList  = ArrayList<Goods>()
    private var suitList  = ArrayList<Goods>()
    private lateinit var oilAdapter: CommonAdapter<Goods>
    private lateinit var saltAdapter: CommonAdapter<Goods>
    private lateinit var suitAdapter: CommonAdapter<Goods>
    private var views = ArrayList<RecyclerView>()
    private var titles = arrayOf("油壶","盐罐","套装" )
    var images = ArrayList<String>()
    override fun getLayoutResources(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {

        swipeRefresh.isRefreshing = true
        swipeRefresh.setOnRefreshListener(this)
        viewModel = ViewModelProviders.of(this,InjectorUtil.getHomeModelFactory()).get(HomeViewModel::class.java)
//        recyclerView.adapter = CommonAdapter<String>(context!!,R.layout.item_goods,viewModel.dataList)
        app_bar_layout.addOnOffsetChangedListener(this)


//        recyclerView.layoutManager = GridLayoutManager(context,2)
//        recyclerView.addItemDecoration(DividerItemDecoration(context!!,BOTH_SET,20, Color.WHITE))
//        recyclerView.adapter = object : CommonAdapter<String>(context!!,R.layout.item_goods,viewModel.dataList) {
//           override fun convert(holder: ViewHolder, t: String) {
//
//           }
//
//       }
        oilRecyclerView = initRecyclerView()
        saltRecyclerView = initRecyclerView()
        suitRecyclerView = initRecyclerView()
        views.add(oilRecyclerView)
        views.add(saltRecyclerView)
        views.add(suitRecyclerView)
        viewpager.addOnPageChangeListener(this)
        viewpager.adapter = CommonPagerAdapter(views, titles)
        banner.setOnBannerListener{
            activity?.newIntent<GoodsDetailActivity>()
        }
        tablayout.setupWithViewPager(viewpager)
        loadData()

    }
    fun initRecyclerView() : RecyclerView{
        var recyclerView = RecyclerView(context!!)
        recyclerView.layoutParams = ViewPager.LayoutParams()
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.addItemDecoration(DividerItemDecoration(context!!,BOTH_SET,20, Color.WHITE))

        return recyclerView
    }
    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        viewModel.getBanners().observe(this, Observer {
                result ->
            var list = result.data?.banners
            if (list != null) {
                for (a in list) {
                    var temp = ServiceCreator.BASE_URL+"image/" + a.image
                    images.add(temp)
                }
                banner?.setImageLoader(GlideImageLoader())
                banner?.setImages(images)
                banner?.start()
            }
            swipeRefresh.isRefreshing = false
        })
        viewModel.getGoods(6).observe(this, Observer {
                result->
            var list = result.data
            oilAdapter = object :CommonAdapter<Goods>(context!!,R.layout.item_goods,list!!){
                override fun convert(holder: ViewHolder, t: Goods) {
                    holder.setText(R.id.name,t.name)
                    holder.setText(R.id.price,t.retailPrice.toString())
                    var img = holder.getView<ImageView>(R.id.img)
                    if (!TextUtils.isEmpty(t.image)){

                        var imgUrl = ServiceCreator.BASE_URL+"image/" + t.image.split(",")[0]
                        Glide.with(context).load(imgUrl).into(img)
                    }
                }

            }

            oilRecyclerView.adapter = oilAdapter
        })
        viewModel.getGoods(9).observe(this, Observer {
                result->
            var list = result.data

            saltAdapter = object :CommonAdapter<Goods>(context!!,R.layout.item_goods,list!!){
                override fun convert(holder: ViewHolder, t: Goods) {
                    holder.setText(R.id.name,t.name)
                    holder.setText(R.id.price,t.retailPrice.toString())
                    var img = holder.getView<ImageView>(R.id.img)
                    if (!TextUtils.isEmpty(t.image)){

                        var imgUrl = ServiceCreator.BASE_URL+"image/" + t.image.split(",")[0]
                        Glide.with(context).load(imgUrl).into(img)
                    }
                }

            }

            saltRecyclerView.adapter = saltAdapter
        })
        viewModel.getGoods(8).observe(this, Observer {
                result->
            var list = result.data

            suitAdapter = object :CommonAdapter<Goods>(context!!,R.layout.item_goods,list!!){
                override fun convert(holder: ViewHolder, t: Goods) {
                    holder.setText(R.id.name,t.name)
                    holder.setText(R.id.price,t.retailPrice.toString())
                    var img = holder.getView<ImageView>(R.id.img)
                    if (!TextUtils.isEmpty(t.image)){

                        var imgUrl = ServiceCreator.BASE_URL+"image/" + t.image.split(",")[0]
                        Glide.with(context).load(imgUrl).into(img)
                    }
                }

            }

            suitRecyclerView.adapter = suitAdapter
        })

    }

    val TAG = "homefragment"
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        Log.d(TAG, "verticalOffset:" + verticalOffset + "  height:" + appBarLayout.getTotalScrollRange())
        val range = appBarLayout.totalScrollRange
        val offset = Math.abs(verticalOffset)
        val rate = offset / java.lang.Float.valueOf(range.toFloat())

        swipeRefresh.isEnabled = rate == 0f
    }
}