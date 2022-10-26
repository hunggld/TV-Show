package com.sildev.tvshows.screen.listtvshow

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.TVShowRepository
import com.sildev.tvshows.data.repository.source.remote.OnResultListener
import com.sildev.tvshows.utils.NetworkHelper

class MainPresenter(private val tvShowRepository: TVShowRepository?) : TVShowsContract.Presenter {

    private var mView: TVShowsContract.View? = null

    override fun getTVShows(status: String, context: Context) {
        if (NetworkHelper.isConnectedToInternet(context)) {
            tvShowRepository?.getTVShows(object : OnResultListener<MutableList<TVShow>> {
                override fun onSuccess(data: MutableList<TVShow>) {
                    mView?.onGetTvShowsSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    mView?.onError(exception)
                }

            }, status)
        } else {
            mView?.onLostInternet()
        }
    }

    override fun loadMoreTVShows(status: String, page: Int, context: Context) {
        if (NetworkHelper.isConnectedToInternet(context)) {
            tvShowRepository?.loadMoreTVShows(object : OnResultListener<MutableList<TVShow>> {
                override fun onSuccess(data: MutableList<TVShow>) {
                    mView?.onLoadMoreTvShowsSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    mView?.onLoadMoreError(exception)
                }

            }, status, page)
        } else {
            mView?.onLostInternet()
        }
    }

    override fun onStart() {
        //Do late
    }

    override fun onStop() {
        //Do late
    }

    override fun setView(view: TVShowsContract.View?) {
        mView = view
    }

}
