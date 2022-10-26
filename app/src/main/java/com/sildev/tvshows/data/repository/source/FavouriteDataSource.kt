package com.sildev.tvshows.data.repository.source

import android.content.Context
import com.sildev.tvshows.data.model.TVShow

interface FavouriteDataSource {

    interface Local {
        fun loadFavouriteList(context: Context): MutableList<TVShow>
        fun removeTvShowFavourite(context: Context, tvShow: TVShow): Boolean
    }
}
