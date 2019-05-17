package com.dbwwt.mall.adapter

import android.content.Context
import com.dbwwt.mall.R
import com.dbwwt.mall.ui.common.CommonAdapter
import com.dbwwt.mall.ui.common.ViewHolder

class EmptyAdapter(context: Context) :
    CommonAdapter<String>(context, R.layout.layout_empty, ArrayList()) {
    override fun convert(holder: ViewHolder, t: String) {

    }

    override fun getItemCount(): Int {
        return 1
    }
}