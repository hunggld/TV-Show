package com.sildev.tvshows.data.model

data class Episode(
    val season: Int = 0, val episode: Int = 0, val name: String = "", val airDate: String = ""
)

object EpisodeEntry {
    const val EPISODES = "episodes"
    const val EPISODE = "episode"
    const val SEASON = "season"
    const val NAME = "name"
    const val AIR_DATE = "air_date"
}
