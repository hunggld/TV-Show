package com.sildev.tvshows

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.SearchRepository
import com.sildev.tvshows.data.repository.source.remote.OnResultListener
import com.sildev.tvshows.screen.listtvshow.SearchContract
import com.sildev.tvshows.screen.listtvshow.SearchPresenter
import com.sildev.tvshows.utils.NetworkHelper
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SearchPresenterTest {
    private val view = mockk<SearchContract.View>(relaxed = true)
    private val repository = mockk<SearchRepository>()
    private val presenter by lazy { SearchPresenter(repository) }
    private val context = mockk<Context>()
    private val key = "the"
    private val listener = slot<OnResultListener<MutableList<TVShow>>>()
    private val dataSuccess = mutableListOf<TVShow>()
    private val exception = Exception()
    @Before
    fun setUp() {
        presenter.setView(view)
        dataSuccess.add(TVShow(0,"12","12","!@","!2","!2","ended"))
    }

    @Test
    fun searchByNameLostInternet() {
        every {
            NetworkHelper.isConnectedToInternet(context)
        } answers {
            false
        }
        presenter.searchByName(key, context)

        verify {
            view.onLostInternet()
        }
    }

    @Test
    fun searchByNameEmpty() {
        presenter.searchByName(" ", context)

        verify {
            view.showProgressLoading()
            view.hideTextEmpty()
            view.onSearchSuccess(mutableListOf())
            view.showTextEmpty()
            view.hideProgressLoading()
        }
    }

    @Test
    fun searchByNameSuccess() {
        every {
            repository.searchByName(capture(listener), key)
        } answers {
            listener.captured.onSuccess(dataSuccess)
        }
        presenter.searchByName(key, context)

        verify {
            view.onSearchSuccess(dataSuccess)
        }
    }
    @Test
    fun searchByNameError() {
        every {
            repository.searchByName(capture(listener), key)
        } answers {
            listener.captured.onError(exception)
        }
        presenter.searchByName(key, context)

        verify {
            view.onSearchError(exception)
        }
    }
}
