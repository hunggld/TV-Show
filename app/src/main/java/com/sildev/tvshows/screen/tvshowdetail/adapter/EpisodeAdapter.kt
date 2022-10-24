package com.sildev.tvshows.screen.tvshowdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sildev.tvshows.data.model.Episode
import com.sildev.tvshows.databinding.ItemEpisodeBinding
import com.sildev.tvshows.utils.FORMAT
import com.sildev.tvshows.utils.base.BaseAdapter
import com.sildev.tvshows.utils.base.BaseViewHolder

class EpisodeAdapter(private val onClickItem: (Episode) -> Unit = { _ -> }) :
    BaseAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>() {
    class EpisodeViewHolder(
        private val itemEpisodeBinding: ItemEpisodeBinding,
        onItemClick: (Episode) -> Unit
    ) : BaseViewHolder<Episode>(itemEpisodeBinding, onItemClick) {

        override fun onBindData(itemData: Episode) {
            super.onBindData(itemData)
            val airDate = AIR_DATE + itemData.airDate
            val title = SEASON + parseInt(itemData.season) + EPISODE + parseInt(itemData.episode)
            itemEpisodeBinding.apply {
                textName.text = itemData.name
                textAirDate.text = airDate
                textTitle.text = title
            }
        }
    }

    companion object {
        private const val AIR_DATE = "Air date: "
        private const val SEASON = "S"
        private const val EPISODE = "E"
        fun parseInt(number: Int) = String.format(FORMAT, number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val itemEpisodeBinding =
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(itemEpisodeBinding, onClickItem)
    }
}
