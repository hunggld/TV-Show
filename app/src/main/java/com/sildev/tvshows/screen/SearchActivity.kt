package com.sildev.tvshows.screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.view.isVisible
import com.sildev.tvshows.R
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.SearchRepository
import com.sildev.tvshows.data.repository.source.remote.SearchRemoteDataSource
import com.sildev.tvshows.databinding.ActivitySearchBinding
import com.sildev.tvshows.screen.listtvshow.SearchContract
import com.sildev.tvshows.screen.listtvshow.SearchPresenter
import com.sildev.tvshows.screen.listtvshow.adapter.TvShowAdapter
import com.sildev.tvshows.utils.A_SECOND
import com.sildev.tvshows.utils.KEY_ID_TV_SHOW
import com.sildev.tvshows.utils.base.BaseActivity
import java.util.*

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate),
    SearchContract.View {

    private val tvShowAdapter = TvShowAdapter(::onClickItem)
    private val searchPresenter: SearchPresenter by lazy {
        SearchPresenter(SearchRepository.getInstance(SearchRemoteDataSource.getInstance()))
    }
    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchPresenter.setView(this)
        binding.recyclerviewResult.adapter = tvShowAdapter
        binding.edittextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do late
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer?.cancel()
            }

            override fun afterTextChanged(p0: Editable?) {
                timer = Timer()
                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        Handler(Looper.getMainLooper()).post {
                            searchPresenter.searchByName(p0.toString())
                        }
                    }
                }, A_SECOND)
            }

        })

    }

    private fun onClickItem(tvShow: TVShow) {
        val intent = Intent(this@SearchActivity, TVShowDetailActivity::class.java)
        intent.putExtra(KEY_ID_TV_SHOW, tvShow)
        startActivity(intent)
    }

    override fun onSearchSuccess(tvShows: MutableList<TVShow>) {
        tvShowAdapter.setData(tvShows)
    }

    override fun onSearchError(exception: Exception?) {
        Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressLoading() {
        binding.progressLoading.isVisible = true
    }

    override fun hideProgressLoading() {
        binding.progressLoading.isVisible = false
    }

    override fun showTextEmpty() {
        binding.textNoResult.isVisible = true
    }

    override fun hideTextEmpty() {
        binding.textNoResult.isVisible = false
    }

}
