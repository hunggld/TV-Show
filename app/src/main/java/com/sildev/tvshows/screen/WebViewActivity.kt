package com.sildev.tvshows.screen

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.sildev.tvshows.databinding.ActivityWebViewBinding
import com.sildev.tvshows.utils.KEY_LINK_TV_SHOW
import com.sildev.tvshows.utils.base.BaseActivity

class WebViewActivity : BaseActivity<ActivityWebViewBinding>(ActivityWebViewBinding::inflate) {

    private val webViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progressLoading.isVisible = true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressLoading.isVisible = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra(KEY_LINK_TV_SHOW) ?: ""
        binding.apply {
            webView.loadUrl(url)
            webView.webViewClient = webViewClient
            webView.settings.javaScriptEnabled = true
            imageBack.setOnClickListener {
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }

    }
}
