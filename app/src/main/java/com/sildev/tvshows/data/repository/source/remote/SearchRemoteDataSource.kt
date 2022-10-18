package com.sildev.tvshows.data.repository.source.remote

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowEntry
import com.sildev.tvshows.data.repository.source.SearchDataSource
import com.sildev.tvshows.data.repository.source.remote.fetchjson.GetJsonFromUrl
import com.sildev.tvshows.utils.BASE_URL
import com.sildev.tvshows.utils.EMPTY_STRING
import com.sildev.tvshows.utils.QUERY_URL
import com.sildev.tvshows.utils.SEARCH_URL

class SearchRemoteDataSource : SearchDataSource.Remote {

    override fun searchByName(
        listener: OnResultListener<MutableList<TVShow>>,
        key: String
    ) {
        GetJsonFromUrl(
            BASE_URL + SEARCH_URL + QUERY_URL + key,
            TVShowEntry.TV_SHOWS,
            listener,
            EMPTY_STRING
        )
    }

    companion object {
        private var instance: SearchRemoteDataSource? = null
        fun getInstance() = synchronized(this) {
            instance ?: SearchRemoteDataSource().also { instance = it }
        }
    }
}
