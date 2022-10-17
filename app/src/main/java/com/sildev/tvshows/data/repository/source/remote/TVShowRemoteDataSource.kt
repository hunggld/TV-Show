package com.sildev.tvshows.data.repository.source.remote

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowEntry
import com.sildev.tvshows.data.repository.source.TVShowDataSource
import com.sildev.tvshows.data.repository.source.remote.fetchjson.GetJsonFromUrl
import com.sildev.tvshows.utils.BASE_PAGE
import com.sildev.tvshows.utils.BASE_POPULAR_TV_SHOW
import com.sildev.tvshows.utils.BASE_URL

class TVShowRemoteDataSource : TVShowDataSource.Remote {

    override fun getTVShows(listener: OnResultListener<MutableList<TVShow>>, status: String) {
        GetJsonFromUrl(
            BASE_URL + BASE_POPULAR_TV_SHOW, TVShowEntry.TV_SHOWS, listener, status
        )
    }

    override fun loadMoreTVShows(
        listener: OnResultListener<MutableList<TVShow>>, status: String, page: Int
    ) {
        GetJsonFromUrl(
            BASE_URL + BASE_POPULAR_TV_SHOW + BASE_PAGE + page.toString(),
            TVShowEntry.TV_SHOWS,
            listener,
            status
        )
    }

    companion object {
        private var instance: TVShowRemoteDataSource? = null
        fun getInstance() = synchronized(this) {
            instance ?: TVShowRemoteDataSource().also { instance = it }
        }
    }

}
