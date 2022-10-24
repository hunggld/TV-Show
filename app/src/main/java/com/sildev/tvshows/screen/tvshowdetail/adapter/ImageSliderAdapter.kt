package com.sildev.tvshows.screen.tvshowdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sildev.tvshows.databinding.ItemSliderImageBinding
import com.sildev.tvshows.utils.base.BaseAdapter
import com.sildev.tvshows.utils.base.BaseViewHolder
import com.sildev.tvshows.utils.setResource

class ImageSliderAdapter(
    private var onClickItem: (String) -> Unit = { _ -> }
) : BaseAdapter<String, ImageSliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val itemView =
            ItemSliderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(itemView, onClickItem)
    }

    class SliderViewHolder(
        private val binding: ItemSliderImageBinding, onClickItem: (String) -> Unit
    ) : BaseViewHolder<String>(binding, onClickItem) {
        override fun onBindData(itemData: String) {
            super.onBindData(itemData)
            binding.imageSlider.setResource(itemData, binding.root.context)
        }
    }
}
