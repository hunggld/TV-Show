package com.sildev.tvshows.data.repository.source

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowDetail
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

interface TvShowDetailDataSource {
    interface Local {
        fun isTvShowFavourite(context: Context, tvShow: TVShow): Boolean
        fun addTvShowFavourite(context: Context, tvShow: TVShow): Boolean
        fun removeTvShowFavourite(context: Context, tvShow: TVShow): Boolean
    }

    interface Remote {
        fun getDetailTvShow(listener: OnResultListener<TVShowDetail>, id: String)
    }
}
