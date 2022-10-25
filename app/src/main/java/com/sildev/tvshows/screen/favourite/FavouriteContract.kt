package com.sildev.tvshows.screen.favourite

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.utils.base.BasePresenter

class FavouriteContract {
    interface View {
        fun onLoadFavouriteSuccess(favouriteList: MutableList<TVShow>)
        fun onLoadFavouriteError()
        fun onRemoveFavouriteSuccess()
        fun onRemoveFavouriteError()
    }

    interface Presenter : BasePresenter<View> {
        fun removeFavourite(context: Context, tvShow: TVShow)
        fun loadFavouriteList(context: Context)

    }
}
