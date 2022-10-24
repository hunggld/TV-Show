package com.sildev.tvshows.screen.tvshowdetail

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowDetail
import com.sildev.tvshows.utils.base.BasePresenter

class DetailContract {
    interface View {
        fun onGetDetailSuccess(detail: TVShowDetail)
        fun onGetDetailError(exception: Exception?)
        fun setFavouriteImage(isFavourite: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getTvShowDetail(id: String)
        fun checkIsFavouriteTvShow(context: Context, tvShow: TVShow)
        fun addTvShowFavourite(context: Context, tvShow: TVShow)
        fun removeTvShowFavourite(context: Context, tvShow: TVShow)
    }
}
