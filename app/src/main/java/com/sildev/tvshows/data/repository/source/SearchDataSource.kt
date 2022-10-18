package com.sildev.tvshows.data.repository.source

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

interface SearchDataSource {
    interface Remote {
        fun searchByName(listener: OnResultListener<MutableList<TVShow>>, key: String)
    }
}
