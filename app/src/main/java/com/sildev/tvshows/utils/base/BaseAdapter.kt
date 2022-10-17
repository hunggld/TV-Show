package com.sildev.tvshows.utils.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {

    private val list: MutableList<T> = mutableListOf()

    override fun getItemCount() = list.size
    override fun onBindViewHolder(viewHolder: VH, position: Int) {
        getItem(position)?.let { viewHolder.onBindData(it) }
    }

    private fun getItem(position: Int): T? =
        if (position in 0 until itemCount) list[position] else null

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
