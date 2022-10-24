package com.sildev.tvshows.data.repository.source.local

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.source.TvShowDetailDataSource
import com.sildev.tvshows.data.repository.source.local.sqlite.DBHelper
import com.sildev.tvshows.data.repository.source.local.sqlite.TvShowDAOImplement

class DetailLocalDataSource : TvShowDetailDataSource.Local {

    override fun isTvShowFavourite(context: Context, tvShow: TVShow): Boolean {
        return getTvShowDAOImplement(context).checkTvShowExist(tvShow) == true
    }

    override fun addTvShowFavourite(context: Context, tvShow: TVShow): Boolean {
        val response = getTvShowDAOImplement(context).insert(tvShow)
        if ((response != null) && (response >= 0)) {
            return true
        }
        return false
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
        private var instance: DetailLocalDataSource? = null
        fun getInstance() = synchronized(this) {
            instance ?: DetailLocalDataSource().also { instance = it }
        }
    }
}
