package com.sildev.tvshows

import android.content.Context
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.TVShowRepository
import com.sildev.tvshows.data.repository.source.remote.OnResultListener
import com.sildev.tvshows.screen.listtvshow.MainPresenter
import com.sildev.tvshows.screen.listtvshow.TVShowsContract
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private val view = mockk<TVShowsContract.View>(relaxed = true)
    private val repository = mockk<TVShowRepository>()
    private val presenter by lazy { MainPresenter(repository) }
    private val listener = slot<OnResultListener<MutableList<TVShow>>>()
    private val dataSuccess = mutableListOf<TVShow>()
    private val context = mockk<Context>()
    private val exception = Exception()

    @Before
    fun setUp() {
        presenter.setView(view)
    }

    @Test
    fun getTvShowsSuccess() {
        every {
            repository.getTVShows(capture(listener), "")
        } answers {
            listener.captured.onSuccess(dataSuccess)
        }
        presenter.getTVShows("", context)
        verify {
            view.onGetTvShowsSuccess(dataSuccess)
        }
    }

    @Test
    fun getTvShowsError() {
        every {
            repository.getTVShows(capture(listener), "")
        } answers {
            listener.captured.onError(exception)
        }
        presenter.getTVShows("", context)
        verify {
            view.onError(exception)
        }
    }

    @Test
    fun loadMoreTvShowsSuccess() {
        every {
            repository.loadMoreTVShows(capture(listener), "", 2)
        } answers {
            listener.captured.onSuccess(dataSuccess)
        }
        presenter.loadMoreTVShows("", 2, context)
        verify {
            view.onLoadMoreTvShowsSuccess(dataSuccess)
        }
    }

    @Test
    fun loadMoreTvShowsError() {
        every {
            repository.loadMoreTVShows(capture(listener), "", 2)
        } answers {
            listener.captured.onError(exception)
        }
        presenter.loadMoreTVShows("", 2, context)
        verify {
            view.onLoadMoreError(exception)
        }
    }
}
