package com.dbwwt.mall.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dbwwt.mall.ui.BaseFragment

class MainPagerAdapter : FragmentStatePagerAdapter {
    var mFragments = ArrayList<BaseFragment>()
    override fun getItem(position: Int): Fragment {
       return mFragments?.get(position)
    }

    override fun getCount(): Int {
        return mFragments?.size
    }
    constructor(activity: AppCompatActivity, fragments:ArrayList<BaseFragment>)
            : super(activity.supportFragmentManager) {
        mFragments.addAll(fragments)
    }


}