package com.sildev.tvshows.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<T : ViewBinding>(private val inflater: (LayoutInflater) -> T) :
    AppCompatActivity() {
    private val _binding: T by lazy { inflater(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
    }


}
