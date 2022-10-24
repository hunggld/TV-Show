package com.sildev.tvshows.data.repository.source.remote

import com.sildev.tvshows.data.model.TVShowDetail
import com.sildev.tvshows.data.model.TVShowDetailEntry
import com.sildev.tvshows.data.repository.source.TvShowDetailDataSource
import com.sildev.tvshows.data.repository.source.remote.fetchjson.GetJsonFromUrl
import com.sildev.tvshows.utils.BASE_URL
import com.sildev.tvshows.utils.DETAIL_URL
import com.sildev.tvshows.utils.EMPTY_STRING
import com.sildev.tvshows.utils.QUERY_URL

class TvShowDetailRemoteDataSource : TvShowDetailDataSource.Remote {

    override fun getDetailTvShow(listener: OnResultListener<TVShowDetail>, id: String) {
        GetJsonFromUrl(BASE_URL+ DETAIL_URL+ QUERY_URL+id, TVShowDetailEntry.TV_SHOW_DETAIL,listener, EMPTY_STRING)
    }

    companion object {
        private var instance: TvShowDetailRemoteDataSource? = null
        fun getInstance() = synchronized(this) {
            instance ?: TvShowDetailRemoteDataSource().also { instance = it }
        }
    }
}
