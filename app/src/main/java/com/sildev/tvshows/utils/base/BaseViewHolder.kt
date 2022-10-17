package com.sildev.tvshows.utils.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<T>(binding: ViewBinding, val onClickItem: (T) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    private var itemData: T? = null

    init {
        itemView.setOnClickListener { itemData?.let { onItemClickListener(it) } }
    }

    open fun onBindData(itemData: T) {
        this.itemData = itemData
    }

    open fun onItemClickListener(itemData: T) = onClickItem(itemData)

}
