package com.sildev.tvshows.data.repository

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.source.TVShowDataSource
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

class TVShowRepository constructor(
    private val remote: TVShowDataSource.Remote, private val local: TVShowDataSource.Local
) : TVShowDataSource.Remote, TVShowDataSource.Local {

    override fun getTVShows(listener: OnResultListener<MutableList<TVShow>>) {
        remote.getTVShows(listener)
    }

    override fun getFavouriteTVShows(listener: OnResultListener<MutableList<TVShow>>) {
        //Do late
    }

    companion object {
        private var instance: TVShowRepository? = null
        fun getInstance(remote: TVShowDataSource.Remote, local: TVShowDataSource.Local) =
            synchronized(this) {
                instance ?: TVShowRepository(remote, local).also { instance = it }
            }
    }
}
