package com.sildev.tvshows.data.repository.source.local

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.source.TVShowDataSource
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

class TVShowLocalDataSource : TVShowDataSource.Local {

    override fun getFavouriteTVShows(listener: OnResultListener<MutableList<TVShow>>) {
        //Do late
    }

    companion object {
        private var instance: TVShowLocalDataSource? = null
        fun getInstance() = synchronized(this) {
            instance ?: TVShowLocalDataSource().also { instance = it }
        }
    }
}
