package com.sildev.tvshows.screen.listtvshow.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.databinding.ItemTvShowBinding
import com.sildev.tvshows.screen.TVShowDetailActivity
import com.sildev.tvshows.utils.KEY_ID_TV_SHOW
import com.sildev.tvshows.utils.RADIUS_TV_SHOW_IMAGE
import com.sildev.tvshows.utils.base.BaseAdapter
import com.sildev.tvshows.utils.base.BaseViewHolder

class TvShowAdapter : BaseAdapter<TVShow, TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemView = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = list[position]

        holder.view.apply {
            textName.text = tvShow.name
            textStatus.text = tvShow.status
            textStartDate.text = tvShow.startDate
            textNetwork.text = tvShow.network
            Glide.with(root.context).load(tvShow.thumbnail)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(RADIUS_TV_SHOW_IMAGE)))
                .into(imageTvShow)
            root.setOnClickListener {
                val intent = Intent(it.context, TVShowDetailActivity::class.java)
                intent.putExtra(KEY_ID_TV_SHOW, tvShow.id)
                root.context.startActivity(intent)
            }
        }


    }

    class TvShowViewHolder(val view: ItemTvShowBinding) : BaseViewHolder(view)

}
