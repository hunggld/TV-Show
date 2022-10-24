package com.sildev.tvshows.data.repository

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowDetail
import com.sildev.tvshows.data.repository.source.TvShowDetailDataSource
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

class TvShowDetailRepository(
    private val remote: TvShowDetailDataSource.Remote,
    private val local: TvShowDetailDataSource.Local
) : TvShowDetailDataSource.Remote, TvShowDetailDataSource.Local {

    override fun getDetailTvShow(listener: OnResultListener<TVShowDetail>, id: String) {
        remote.getDetailTvShow(listener, id)
    }

    override fun isTvShowFavourite(context: Context, tvShow: TVShow): Boolean {
        return local.isTvShowFavourite(context, tvShow)
    }

    override fun addTvShowFavourite(context: Context, tvShow: TVShow): Boolean {
        return local.addTvShowFavourite(context, tvShow)
    }

    override fun removeTvShowFavourite(context: Context, tvShow: TVShow): Boolean {
        return local.removeTvShowFavourite(context, tvShow)
    }

    companion object {
        private var instance: TvShowDetailRepository? = null
        fun getInstance(
            remote: TvShowDetailDataSource.Remote, local: TvShowDetailDataSource.Local
        ) = synchronized(this) {
            instance ?: TvShowDetailRepository(remote, local).also { instance = it }
        }
    }
}
