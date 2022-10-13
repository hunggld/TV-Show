package com.sildev.tvshows.screen

import android.os.Bundle
import com.sildev.tvshows.data.repository.TVShowRepository
import com.sildev.tvshows.data.repository.source.local.TVShowLocalDataSource
import com.sildev.tvshows.data.repository.source.remote.TVShowRemoteDataSource
import com.sildev.tvshows.databinding.ActivityMainBinding
import com.sildev.tvshows.screen.listtvshow.MainPresenter
import com.sildev.tvshows.utils.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
