package com.sildev.tvshows.screen.favourite

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.FavouriteRepository

class FavouritePresenter(private val favouriteRepository: FavouriteRepository) :
    FavouriteContract.Presenter {

    private var mView: FavouriteContract.View? = null

    override fun removeFavourite(context: Context, tvShow: TVShow) {
        val response = favouriteRepository.removeTvShowFavourite(context, tvShow)
        if (response) {
            mView?.onRemoveFavouriteSuccess()
        } else {
            mView?.onRemoveFavouriteError()
        }
    }

    override fun loadFavouriteList(context: Context) {
        val favouriteList = favouriteRepository.loadFavouriteList(context)
        if (favouriteList.isEmpty()){
            mView?.onLoadFavouriteError()
        }else{
            mView?.onLoadFavouriteSuccess(favouriteList)
        }

    }

    override fun onStart() {
        //Do late
    }

    override fun onStop() {
        //Do late
    }

    override fun setView(view: FavouriteContract.View?) {
        mView = view
    }
}
