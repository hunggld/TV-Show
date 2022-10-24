package com.sildev.tvshows.screen.listtvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.databinding.ItemTvShowBinding
import com.sildev.tvshows.utils.base.BaseAdapter
import com.sildev.tvshows.utils.base.BaseViewHolder
import com.sildev.tvshows.utils.setResource

class TvShowAdapter(private var onClickItem: (TVShow) -> Unit = { _ -> }) :
    BaseAdapter<TVShow, TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemView = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemView, onClickItem)
    }

    class TvShowViewHolder(
        private val binding: ItemTvShowBinding, onClickItem: (TVShow) -> Unit
    ) : BaseViewHolder<TVShow>(binding, onClickItem) {
        override fun onBindData(itemData: TVShow) {
            super.onBindData(itemData)
            binding.apply {
                textName.text = itemData.name
                textStatus.text = itemData.status
                textStartDate.text = itemData.startDate
                textNetwork.text = itemData.network
                imageTvShow.setResource(itemData.thumbnail, root.context)
            }
        }
    }

}
