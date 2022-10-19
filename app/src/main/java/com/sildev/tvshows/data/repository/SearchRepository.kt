package com.sildev.tvshows.data.repository

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.source.SearchDataSource
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

class SearchRepository(private val remote: SearchDataSource.Remote) : SearchDataSource.Remote {

    override fun searchByName(listener: OnResultListener<MutableList<TVShow>>, key: String) {
        remote.searchByName(listener, key)
    }

    companion object {
        private var instance: SearchRepository? = null
        fun getInstance(remote: SearchDataSource.Remote) = synchronized(this) {
            instance ?: SearchRepository(remote).also { instance = it }
        }
    }
}
