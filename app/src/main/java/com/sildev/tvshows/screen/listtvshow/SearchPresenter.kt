package com.sildev.tvshows.screen.listtvshow

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.SearchRepository
import com.sildev.tvshows.data.repository.source.remote.OnResultListener

class SearchPresenter(private val searchRepository: SearchRepository) : SearchContract.Presenter {

    private var mView: SearchContract.View? = null

    override fun searchByName(key: String) {
        mView?.showProgressLoading()
        mView?.hideTextEmpty()
        if (key.trim().isEmpty()) {
            mView?.onSearchSuccess(mutableListOf())
            mView?.showTextEmpty()
            mView?.hideProgressLoading()
        } else {
            searchRepository.searchByName(object : OnResultListener<MutableList<TVShow>> {
                override fun onSuccess(data: MutableList<TVShow>) {
                    mView?.hideProgressLoading()
                    mView?.onSearchSuccess(data)
                    if (data.isEmpty()) mView?.showTextEmpty()
                }

                override fun onError(exception: Exception?) {
                    mView?.hideProgressLoading()
                    mView?.onSearchError(exception)
                }

            }, key)
        }

    }

    override fun onStart() {
        // Do late
    }

    override fun onStop() {
        // Do late
    }

    override fun setView(view: SearchContract.View?) {
        mView = view
    }
}
