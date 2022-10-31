package com.sildev.tvshows

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.FavouriteRepository
import com.sildev.tvshows.screen.favourite.FavouriteContract
import com.sildev.tvshows.screen.favourite.FavouritePresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class FavouritePresenterTest {
    private val view = mockk<FavouriteContract.View>(relaxed = true)
    private val repository = mockk<FavouriteRepository>()
    private val presenter by lazy { FavouritePresenter(repository) }
    private val context = mockk<Context>()
    private val dataSuccess = mock<MutableList<TVShow>>()
    private val item = mockk<TVShow>()

    @Before
    fun setUp() {
        presenter.setView(view)
    }

    @Test
    fun loadFavouriteSuccess() {
        every {
            repository.loadFavouriteList(context)
        } answers {
            dataSuccess
        }
        presenter.loadFavouriteList(context)
        verify {
            view.onLoadFavouriteSuccess(dataSuccess)
        }
    }

    @Test
    fun loadFavouriteError() {
        every {
            repository.loadFavouriteList(context)
        } answers {
            mutableListOf()
        }
        presenter.loadFavouriteList(context)
        verify {
            view.onLoadFavouriteError()
        }
    }

    @Test
    fun removeFavouriteSuccess() {
        every {
            repository.removeTvShowFavourite(context, item)
        } answers {
            true
        }
        presenter.removeFavourite(context, item)
        verify {
            view.onRemoveFavouriteSuccess()
        }
    }

    @Test
    fun removeFavouriteError() {
        every {
            repository.removeTvShowFavourite(context, item)
        } answers {
            false
        }
        presenter.removeFavourite(context, item)
        verify {
            view.onRemoveFavouriteError()
        }
    }
}
