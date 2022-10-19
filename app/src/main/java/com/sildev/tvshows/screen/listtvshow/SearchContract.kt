package com.sildev.tvshows.screen.listtvshow

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.utils.base.BasePresenter

class SearchContract {
    interface View {
        fun onSearchSuccess(tvShows: MutableList<TVShow>)
        fun onSearchError(exception: Exception?)
        fun showProgressLoading()
        fun hideProgressLoading()
        fun showTextEmpty()
        fun hideTextEmpty()
    }

    interface Presenter : BasePresenter<View> {
        fun searchByName(key: String)
    }
}
