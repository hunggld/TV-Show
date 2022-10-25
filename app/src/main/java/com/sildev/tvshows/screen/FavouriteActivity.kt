package com.sildev.tvshows.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.sildev.tvshows.R
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.repository.FavouriteRepository
import com.sildev.tvshows.data.repository.source.local.FavouriteLocalDataSource
import com.sildev.tvshows.databinding.ActivityFavouriteBinding
import com.sildev.tvshows.screen.favourite.FavouriteContract
import com.sildev.tvshows.screen.favourite.FavouritePresenter
import com.sildev.tvshows.screen.listtvshow.adapter.TvShowAdapter
import com.sildev.tvshows.utils.KEY_ID_TV_SHOW
import com.sildev.tvshows.utils.base.BaseActivity

class FavouriteActivity : BaseActivity<ActivityFavouriteBinding>(ActivityFavouriteBinding::inflate),
    FavouriteContract.View {

    private val tvShowAdapter = TvShowAdapter(::onClickItem, ::onLongClickItem)
    private val favouritePresenter: FavouritePresenter by lazy {
        FavouritePresenter(FavouriteRepository.getInstance(FavouriteLocalDataSource.getInstance()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            recyclerviewFavourite.adapter = tvShowAdapter
            imageBack.setOnClickListener { onBackPressed() }
        }
        favouritePresenter.setView(this)
        favouritePresenter.loadFavouriteList(this)

    }

    override fun onLoadFavouriteSuccess(favouriteList: MutableList<TVShow>) {
        binding.textEmpty.isVisible = false
        binding.textHint.isVisible = true
        tvShowAdapter.setData(favouriteList)
    }

    override fun onLoadFavouriteError() {
        binding.textEmpty.isVisible = true
        binding.textHint.isVisible = false
    }

    override fun onRemoveFavouriteSuccess() {
        Toast.makeText(this, getString(R.string.remove_success), Toast.LENGTH_SHORT).show()
        favouritePresenter.loadFavouriteList(this)
    }

    override fun onRemoveFavouriteError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    private fun onClickItem(tvShow: TVShow) {
        val intent = Intent(this@FavouriteActivity, TVShowDetailActivity::class.java)
        intent.putExtra(KEY_ID_TV_SHOW, tvShow)
        startActivity(intent)
    }

    private fun onLongClickItem(tvShow: TVShow) {
        val alertRemoveDialog = AlertDialog.Builder(this)
        val message = "Do you want remove " + tvShow.name + " from favourites"
        alertRemoveDialog.setMessage(message)
        alertRemoveDialog.setNegativeButton(
            getString(R.string.no)
        ) { p0, _ -> p0?.dismiss() }
        alertRemoveDialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
            favouritePresenter.removeFavourite(this@FavouriteActivity, tvShow)
        }
        alertRemoveDialog.show()
    }

    override fun onResume() {
        favouritePresenter.loadFavouriteList(this)
        super.onResume()
    }
}
