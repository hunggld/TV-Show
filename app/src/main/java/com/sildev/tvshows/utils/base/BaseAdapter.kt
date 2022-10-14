package com.sildev.tvshows.utils.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseViewHolder> : RecyclerView.Adapter<VH>() {

    val list: MutableList<T> = mutableListOf()

    override fun getItemCount() = list.size

    fun setData(dataList: MutableList<T>) {
        list.apply {
            clear()
            addAll(dataList)
        }
        notifyDataSetChanged()
    }

    fun insertData(dataList: MutableList<T>) {
        val oldSize = list.size
        list.addAll(dataList)
        notifyItemRangeChanged(oldSize, list.size)
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, list.size)
    }
}
