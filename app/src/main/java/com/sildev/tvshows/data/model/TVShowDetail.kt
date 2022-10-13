package com.sildev.tvshows.data.model

import org.json.JSONArray

data class TVShowDetail(
    val url: String = "",
    val description: String = "",
    val runtime: Int = 0,
    val imagePath: String = "",
    val rating: String = "",
    val genres: MutableList<String> = mutableListOf(),
    val pictures: MutableList<String> = mutableListOf(),
    val episodes: MutableList<Episode> = mutableListOf()

)

object TVShowDetailEntry {
    const val TV_SHOW_DETAIL = "tvShow"
    const val URL = "url"
    const val DESCRIPTION = "description"
    const val RUNTIME = "runtime"
    const val IMAGE_PATH = "image_path"
    const val RATING = "rating"
    const val GENRES = "genres"
    const val PICTURES = "pictures"
    const val EPISODES = "episodes"
}
