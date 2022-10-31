package com.sildev.tvshows

import android.content.Context
import android.widget.Toast
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowDetail
import com.sildev.tvshows.data.repository.TvShowDetailRepository
import com.sildev.tvshows.data.repository.source.remote.OnResultListener
import com.sildev.tvshows.screen.tvshowdetail.DetailContract
import com.sildev.tvshows.screen.tvshowdetail.DetailPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DetailPresenterTest {
    private val view = mockk<DetailContract.View>(relaxed = true)
    private val repository = mockk<TvShowDetailRepository>()
    private val presenter by lazy { DetailPresenter(repository) }
    private val listener = slot<OnResultListener<TVShowDetail>>()
    private val dataSuccess = TVShowDetail()
    private val id = "12"
    private val context = mockk<Context>()
    private val exception = Exception()
    private val tvShow = mockk<TVShow>()


    @Before
    fun setUp() {
        presenter.setView(view)
    }

    @Test
    fun getTVShowDetailSuccess() {
        every {
            repository.getDetailTvShow(capture(listener), id)
        } answers {
            listener.captured.onSuccess(dataSuccess)
        }
        presenter.getTvShowDetail(id, context)
        verify {
            view.onGetDetailSuccess(dataSuccess)
        }
    }

    @Test
    fun getTVShowDetailError() {
        every {
            repository.getDetailTvShow(capture(listener), id)
        } answers {
            listener.captured.onError(exception)
        }
        presenter.getTvShowDetail(id, context)
        verify {
            view.onGetDetailError(exception)
        }
    }

    @Test
    fun checkIsFavouriteTvShow() {
        every {
            repository.isTvShowFavourite(context, tvShow)
        } answers {
            true
        }
        presenter.checkIsFavouriteTvShow(context, tvShow)
        verify {
            view.setFavouriteImage(true)
        }
    }

    @Test
    fun addFavourite() {
        every {
            repository.addTvShowFavourite(context, tvShow)
        } answers {
            true
        }
        presenter.addTvShowFavourite(context, tvShow)
        verify {
            view.setFavouriteImage(true)
        }
    }
    @Test
    fun removeFavourite() {
        every {
            repository.removeTvShowFavourite(context, tvShow)
        } answers {
            true
        }
        presenter.removeTvShowFavourite(context, tvShow)
        verify {
            view.setFavouriteImage(false)
        }
    }
}
