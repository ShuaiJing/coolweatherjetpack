package com.dbwwt.mall.ui.common

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

open class CommonPagerAdapter<T : View>(private val mList: List<T>?, private val titles :Array<String>) : PagerAdapter() {
    override fun getCount(): Int {
        if (mList == null) {
            return 0
        }
        Log.d(TAG, "page count:" + mList.size)
        return mList.size
    }

    private val TAG = "CommonPagerAdapter"


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mList!![position])
        return mList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mList!![position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        Log.d("pagenum",""+position + titles.toString())
        if (position<=titles.size)
        return titles[position]
        return ""
    }
}