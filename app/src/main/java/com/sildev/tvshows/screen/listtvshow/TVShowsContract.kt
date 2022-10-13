package com.sildev.tvshows.screen.listtvshow

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.utils.base.BasePresenter
import java.lang.Exception

class TVShowsContract {
    interface View {
        fun onGetTvShowsSuccess(movies: MutableList<TVShow>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getTVShows()
    }
}
