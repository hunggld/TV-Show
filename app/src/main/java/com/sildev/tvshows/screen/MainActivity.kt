package com.sildev.tvshows.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sildev.tvshows.R
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.TVShowRepository
import com.sildev.tvshows.data.repository.source.local.TVShowLocalDataSource
import com.sildev.tvshows.data.repository.source.remote.TVShowRemoteDataSource
import com.sildev.tvshows.databinding.ActivityMainBinding
import com.sildev.tvshows.screen.listtvshow.MainPresenter
import com.sildev.tvshows.screen.listtvshow.TVShowsContract
import com.sildev.tvshows.screen.listtvshow.adapter.TvShowAdapter
import com.sildev.tvshows.utils.KEY_ID_TV_SHOW
import com.sildev.tvshows.utils.TYPE_ENDED
import com.sildev.tvshows.utils.TYPE_RUNNING
import com.sildev.tvshows.utils.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    TVShowsContract.View {
    private var tvShowAdapter = TvShowAdapter(::onClickItem)
    private var currentPage = 1
    private var type = ""
    private val mainPresenter: MainPresenter by lazy {
        MainPresenter(
            TVShowRepository.getInstance(
                TVShowRemoteDataSource.getInstance(), TVShowLocalDataSource.getInstance()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerviewTvShows.apply {
            adapter = tvShowAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    val sizeData = tvShowAdapter.itemCount - 1
                    if (linearLayoutManager != null &&
                        linearLayoutManager.findLastCompletelyVisibleItemPosition() == sizeData) {
                        currentPage += 1
                        binding.progressLoadingMore.isVisible = true
                        mainPresenter.loadMoreTVShows(type, currentPage)
                    }
                }
            })
        }
        mainPresenter.setView(this)
        mainPresenter.getTVShows(type)
        binding.tbMain.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_favourite -> {
                    startActivity(Intent(this@MainActivity, FavouriteActivity::class.java))
                }
                R.id.item_search -> {
                    startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                }
                R.id.item_all -> {
                    binding.progressLoading.isVisible = true
                    type = ""
                    mainPresenter.getTVShows(type)
                }
                R.id.item_running -> {
                    binding.progressLoading.isVisible = true
                    type = TYPE_RUNNING
                    mainPresenter.getTVShows(type)
                }
                R.id.item_end -> {
                    binding.progressLoading.isVisible = true
                    type = TYPE_ENDED
                    mainPresenter.getTVShows(type)
                }
            }
            true
        }
    }

    private fun onClickItem(tvShow: TVShow) {
        val intent = Intent(this@MainActivity, TVShowDetailActivity::class.java)
        intent.putExtra(KEY_ID_TV_SHOW, tvShow)
        startActivity(intent)
    }

    override fun onGetTvShowsSuccess(movies: MutableList<TVShow>) {
        tvShowAdapter.setData(movies)
        binding.progressLoading.isVisible = false
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(this, getString(R.string.error_load_tvshow), Toast.LENGTH_SHORT).show()
    }

    override fun onLoadMoreTvShowsSuccess(movies: MutableList<TVShow>) {
        binding.progressLoadingMore.isVisible = false
        tvShowAdapter.insertData(movies)
    }

    override fun onLoadMoreError(exception: Exception?) {
        Toast.makeText(this, getString(R.string.error_load_tvshow), Toast.LENGTH_SHORT).show()
    }

}
