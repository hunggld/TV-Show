package com.sildev.tvshows.data.repository.source.local

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.source.FavouriteDataSource
import com.sildev.tvshows.data.repository.source.local.sqlite.DBHelper
import com.sildev.tvshows.data.repository.source.local.sqlite.TvShowDAOImplement

class FavouriteLocalDataSource : FavouriteDataSource.Local {

    override fun loadFavouriteList(context: Context): MutableList<TVShow> {
        return getTvShowDAOImplement(context).getAll()
    }


    override fun removeTvShowFavourite(context: Context, tvShow: TVShow): Boolean {
        val response = getTvShowDAOImplement(context).delete(tvShow)
        if ((response != null) && (response >= 0)) {
            return true
        }
        return false
    }

    private fun getTvShowDAOImplement(context: Context): TvShowDAOImplement {
        return TvShowDAOImplement.getInstance(DBHelper.getInstance(context))
    }

    companion object {

        private var instance: FavouriteLocalDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: FavouriteLocalDataSource().also { instance = it }
        }
    }
}
