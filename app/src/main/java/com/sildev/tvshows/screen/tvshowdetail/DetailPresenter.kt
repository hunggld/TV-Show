package com.sildev.tvshows.screen.tvshowdetail

import android.content.Context
import android.widget.Toast
import com.sildev.tvshows.R
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowDetail
import com.sildev.tvshows.data.repository.TvShowDetailRepository
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

class DetailPresenter(private val tvShowDetailRepository: TvShowDetailRepository) :
    DetailContract.Presenter {

    private var mView: DetailContract.View? = null

    override fun getTvShowDetail(id: String) {
        tvShowDetailRepository.getDetailTvShow(object : OnResultListener<TVShowDetail> {
            override fun onSuccess(data: TVShowDetail) {
                mView?.onGetDetailSuccess(data)
            }

            override fun onError(exception: Exception?) {
                mView?.onGetDetailError(exception)
            }

        }, id)
    }

    override fun checkIsFavouriteTvShow(context: Context, tvShow: TVShow) {
        mView?.setFavouriteImage(tvShowDetailRepository.isTvShowFavourite(context, tvShow))
    }

    override fun addTvShowFavourite(context: Context, tvShow: TVShow) {
        val response = tvShowDetailRepository.addTvShowFavourite(context, tvShow)
        if (response) {
            mView?.setFavouriteImage(true)
            Toast.makeText(context, context.getString(R.string.add_success), Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_SHORT).show()
        }

    }

    override fun removeTvShowFavourite(context: Context, tvShow: TVShow) {
        val response = tvShowDetailRepository.removeTvShowFavourite(context, tvShow)
        if (response) {
            mView?.setFavouriteImage(false)
            Toast.makeText(context, context.getString(R.string.remove_success), Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
    }


    override fun onStart() {
        //  Do late
    }

    override fun onStop() {
        // Do late
    }

    override fun setView(view: DetailContract.View?) {
        mView = view
    }
}
