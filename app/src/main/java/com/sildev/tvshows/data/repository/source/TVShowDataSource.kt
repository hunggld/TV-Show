package com.sildev.tvshows.data.repository.source

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

interface TVShowDataSource {
    interface Local {
        fun getFavouriteTVShows(listener: OnResultListener<MutableList<TVShow>>)
    }

    interface Remote {
        fun getTVShows(listener: OnResultListener<MutableList<TVShow>>)
    }
}
