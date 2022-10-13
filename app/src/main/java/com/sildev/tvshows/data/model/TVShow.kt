package com.sildev.tvshows.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow(
    val id: Int = 0,
    val name: String = "",
    val startDate: String = "",
    val country: String = "",
    val network: String = "",
    val thumbnail: String = "",
    val status: String = ""
) : Parcelable

object TVShowEntry {
    const val TV_SHOWS = "tv_shows"
    const val ID = "id"
    const val NAME = "name"
    const val START_DATE = "start_date"
    const val COUNTRY = "country"
    const val NETWORK = "network"
    const val THUMBNAIL = "image_thumbnail_path"
    const val STATUS = "status"
}
