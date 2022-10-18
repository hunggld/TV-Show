package com.sildev.tvshows.screen.listtvshow

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.utils.base.BasePresenter

class TVShowsContract {

    interface View {
        fun onGetTvShowsSuccess(movies: MutableList<TVShow>)
        fun onError(exception: Exception?)
        fun onLoadMoreTvShowsSuccess(movies: MutableList<TVShow>)
        fun onLoadMoreError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getTVShows(status: String)
        fun loadMoreTVShows(status: String, page: Int)
    }
}
