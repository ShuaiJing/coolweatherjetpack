package com.dbwwt.mall.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class CommonAdapter<T> : RecyclerView.Adapter<ViewHolder> {
    protected var mContext: Context
    protected var mLayoutId: Int = 0
    var datas: List<T>? = null
    protected var mInflater: LayoutInflater
    protected var mOnItemClickListener: OnItemClickListener? = null

    constructor(context: Context, layoutId: Int) {
        mContext = context
        mInflater = LayoutInflater.from(context)
        mLayoutId = layoutId
    }

    constructor(context: Context, layoutId: Int, datas: List<T>) {
        mContext = context
        mInflater = LayoutInflater.from(context)
        mLayoutId = layoutId
        this.datas = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder.createViewHolder(mContext, parent, mLayoutId)
        setListener(parent, viewHolder, viewType)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //        holder.updatePosition(position);
        if (datas != null&& datas!!.size>position) {
            convert(holder, datas!![position])
        }

    }

    abstract fun convert(holder: ViewHolder, t: T)

    override fun getItemCount(): Int {
        return  datas!!.size
    }

    protected fun isEnabled(viewType: Int): Boolean {
        return true
    }

    protected fun setListener(parent: ViewGroup, viewHolder: ViewHolder, viewType: Int) {
        if (!isEnabled(viewType)) return
        viewHolder.convertView.setOnClickListener(View.OnClickListener { v ->
            if (mOnItemClickListener != null) {
                val position = viewHolder.adapterPosition
                if (position >= 0 && position <= itemCount) {
                    mOnItemClickListener!!.onItemClick(v, viewHolder, position)
                }
            }
        })

        viewHolder.convertView.setOnLongClickListener(View.OnLongClickListener { v ->
            if (mOnItemClickListener != null) {
                val position = viewHolder.adapterPosition
                return@OnLongClickListener mOnItemClickListener!!.onItemLongClick(v, viewHolder, position)
            }
            false
        })
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, holder: RecyclerView.ViewHolder, position: Int)

        fun onItemLongClick(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean
    }

}