package com.sildev.tvshows.screen

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sildev.tvshows.R
import com.sildev.tvshows.data.model.Episode
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowDetail
import com.sildev.tvshows.data.repository.TvShowDetailRepository
import com.sildev.tvshows.data.repository.source.local.DetailLocalDataSource
import com.sildev.tvshows.data.repository.source.remote.TvShowDetailRemoteDataSource
import com.sildev.tvshows.databinding.ActivityTvshowDetailBinding
import com.sildev.tvshows.databinding.BottomsheetEpisodeBinding
import com.sildev.tvshows.screen.tvshowdetail.DetailContract
import com.sildev.tvshows.screen.tvshowdetail.DetailPresenter
import com.sildev.tvshows.screen.tvshowdetail.adapter.EpisodeAdapter
import com.sildev.tvshows.screen.tvshowdetail.adapter.ImageSliderAdapter
import com.sildev.tvshows.utils.DESCRIPTION_LINE_MAX
import com.sildev.tvshows.utils.KEY_ID_TV_SHOW
import com.sildev.tvshows.utils.KEY_LINK_TV_SHOW
import com.sildev.tvshows.utils.base.BaseActivity
import com.sildev.tvshows.utils.NetworkHelper
import com.sildev.tvshows.utils.setResource

class TVShowDetailActivity :
    BaseActivity<ActivityTvshowDetailBinding>(ActivityTvshowDetailBinding::inflate),
    DetailContract.View {

    private val detailPresenter: DetailPresenter by lazy {
        DetailPresenter(
            TvShowDetailRepository.getInstance(
                TvShowDetailRemoteDataSource.getInstance(), DetailLocalDataSource.getInstance()
            )
        )
    }
    private val episodeAdapter = EpisodeAdapter()
    private val episodeBottomSheetDialog: BottomSheetDialog by lazy {
        BottomSheetDialog(this@TVShowDetailActivity)
    }
    private val episodeList = mutableListOf<Episode>()
    private val sliderAdapter = ImageSliderAdapter()
    private val tvShow by lazy {
        intent.getParcelableExtra(KEY_ID_TV_SHOW) ?: TVShow()
    }
    private var tvShowDetail = TVShowDetail()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailPresenter.setView(this)
        detailPresenter.getTvShowDetail(tvShow.id.toString(), this)

        binding.apply {
            imageBack.setOnClickListener { finish() }
            textReadMore.setOnClickListener {
                onClickReadMore()
            }
            btnWebsite.setOnClickListener {
                val intent = Intent(this@TVShowDetailActivity, WebViewActivity::class.java)
                intent.putExtra(KEY_LINK_TV_SHOW, tvShowDetail.url)
                startActivity(intent)
            }
            btnEpisode.setOnClickListener {
                episodeBottomSheetDialog.show()
            }
            imageFavourite.setOnClickListener {
                if (tvShowDetail.isFavourite) {
                    detailPresenter.removeTvShowFavourite(this@TVShowDetailActivity, tvShow)
                } else {
                    detailPresenter.addTvShowFavourite(this@TVShowDetailActivity, tvShow)
                }
            }
        }
        setUpBottomSheet()
    }

    private fun setUpBottomSheet() {
        val bottomSheetEpisodeBinding = BottomsheetEpisodeBinding.inflate(layoutInflater)
        episodeBottomSheetDialog.setContentView(bottomSheetEpisodeBinding.root)
        bottomSheetEpisodeBinding.apply {
            recyclerviewEpisode.adapter = episodeAdapter
            imageClose.setOnClickListener {
                episodeBottomSheetDialog.hide()
            }
        }

    }

    private fun onClickReadMore() {
        binding.apply {
            if (textReadMore.text.equals(getString(R.string.read_more))) {
                textDescription.maxLines = Int.MAX_VALUE
                textReadMore.text = getString(R.string.read_less)
            } else {
                textDescription.maxLines = DESCRIPTION_LINE_MAX
                textReadMore.text = getString(R.string.read_more)
            }
        }
    }

    override fun onGetDetailSuccess(detail: TVShowDetail) {
        tvShowDetail = detail
        binding.progressLoading.isVisible = false
        episodeList.addAll(detail.episodes)
        episodeAdapter.setData(detail.episodes)
        val startDate = getString(R.string.started_on) + tvShow.startDate
        val runtime = detail.runtime.toString() + getString(R.string.min)
        binding.apply {
            layoutDetail.isVisible = true
            imageTvShow.setResource(detail.imagePath, this@TVShowDetailActivity)
            textName.text = tvShow.name
            textStatus.text = tvShow.status
            textNetwork.text = tvShow.network
            textStartDate.text = startDate
            textRating.text = detail.rating
            textDuration.text = runtime
            textGenre.text = detail.genres[0]
            textDescription.text = detail.description
        }
        loadImageSlider(detail.pictures)
        detailPresenter.checkIsFavouriteTvShow(this, tvShow)
    }

    override fun onGetDetailError(exception: Exception?) {
        binding.textError.isVisible = true
        binding.progressLoading.isVisible = false
    }

    override fun setFavouriteImage(isFavourite: Boolean) {
        tvShowDetail.isFavourite = isFavourite
        val resourceImage = if (isFavourite) {
            R.drawable.background_is_favourite
        } else {
            R.drawable.background_not_favourite
        }
        binding.imageFavourite.setBackgroundResource(resourceImage)
    }

    override fun onLostInternet() {
        val alertInternetDialog = NetworkHelper.getNetworkAlertDialog(this)
        alertInternetDialog.setPositiveButton(getString(R.string.ok)) { _, _ -> finish() }
        alertInternetDialog.show()
    }

    private fun loadImageSlider(images: MutableList<String>) {
        binding.viewPagerSlider.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        binding.viewPagerSlider.adapter = sliderAdapter
        sliderAdapter.setData(images)
        setUpSliderIndicators(images.size)
        binding.viewPagerSlider.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentSliderIndicators(position)
            }
        })
    }

    private fun setUpSliderIndicators(count: Int) {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            MARGIN_INDICATOR_8, MARGIN_INDICATOR_0, MARGIN_INDICATOR_8, MARGIN_INDICATOR_0
        )
        for (i in 1..count) {
            val imageIndicator = ImageView(applicationContext)
            imageIndicator.setImageDrawable(getDrawable(R.drawable.background_indicator_inactive))
            imageIndicator.layoutParams = params
            binding.layoutSliderIndicators.addView(imageIndicator)
        }
        setCurrentSliderIndicators(CURRENT_SLIDER_DEFAULT)
    }

    private fun setCurrentSliderIndicators(position: Int) {
        val childCount = binding.layoutSliderIndicators.childCount.minus(1)

        for (i in 0..childCount) {
            val imageView = binding.layoutSliderIndicators.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(getDrawable(R.drawable.background_indicator_active))
            } else {
                imageView.setImageDrawable(getDrawable(R.drawable.background_indicator_inactive))
            }
        }
    }

    companion object {
        const val MARGIN_INDICATOR_8 = 8
        const val MARGIN_INDICATOR_0 = 0
        const val CURRENT_SLIDER_DEFAULT = 0
    }

}
