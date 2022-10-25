package com.sildev.tvshows.data.repository

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.source.FavouriteDataSource

class FavouriteRepository(private val local: FavouriteDataSource.Local) :
    FavouriteDataSource.Local {

    override fun loadFavouriteList(context: Context): MutableList<TVShow> {
        return local.loadFavouriteList(context)
    }

    override fun removeTvShowFavourite(context: Context, tvShow: TVShow): Boolean {
        return local.removeTvShowFavourite(context, tvShow)
    }

    companion object {
        private var instance: FavouriteRepository? = null
        fun getInstance(local: FavouriteDataSource.Local) = synchronized(this) {
            instance ?: FavouriteRepository(local).also { instance = it }
        }
    }
}
