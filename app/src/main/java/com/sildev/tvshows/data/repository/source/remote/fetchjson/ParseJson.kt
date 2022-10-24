package com.sildev.tvshows.data.repository.source.remote.fetchjson

import com.sildev.tvshows.data.model.TVShowDetail
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowEntry
import com.sildev.tvshows.data.model.TVShowDetailEntry
import com.sildev.tvshows.data.model.Episode
import com.sildev.tvshows.data.model.EpisodeEntry
import org.json.JSONObject

class ParseJson {

    fun tvShowParseJson(jsonObject: JSONObject): TVShow {
        jsonObject.let {
            val id = it.getInt(TVShowEntry.ID)
            val name = it.getString(TVShowEntry.NAME)
            val network = it.getString(TVShowEntry.NETWORK)
            val country = it.getString(TVShowEntry.COUNTRY)
            val startDate = it.getString(TVShowEntry.START_DATE)
            val thumbnail = it.getString(TVShowEntry.THUMBNAIL)
            val status = it.getString(TVShowEntry.STATUS)
            return TVShow(id, name, startDate, country, network, thumbnail, status)
        }
    }

    fun tvShowDetailParseJson(jsonObject: JSONObject): TVShowDetail {
        jsonObject.let {
            val url = it.getString(TVShowDetailEntry.URL)
            val description = it.getString(TVShowDetailEntry.DESCRIPTION)
            val runtime = it.getInt(TVShowDetailEntry.RUNTIME)
            val imagePath = it.getString(TVShowDetailEntry.IMAGE_PATH)
            val rating = it.getString(TVShowDetailEntry.RATING)
            val genres = mutableListOf<String>()
            val pictures = mutableListOf<String>()
            val episodes = mutableListOf<Episode>()
            val isFavourite = false

            val genreJsonArray = it.getJSONArray(TVShowDetailEntry.GENRES)
            for (i in 0 until genreJsonArray.length()) {
                genres.add(genreJsonArray.getString(i))
            }
            val pictureJsonArray = it.getJSONArray(TVShowDetailEntry.PICTURES)
            for (i in 0 until pictureJsonArray.length()) {
                pictures.add(pictureJsonArray.getString(i))
            }
            val episodeJsonArray = it.getJSONArray(TVShowDetailEntry.EPISODES)
            for (i in 0 until episodeJsonArray.length()) {
                episodes.add(episodeParseJson(episodeJsonArray.getJSONObject(i)))
            }

            return TVShowDetail(
                url,
                description,
                runtime,
                imagePath,
                rating,
                genres,
                pictures,
                episodes,
                isFavourite
            )
        }
    }

    private fun episodeParseJson(jsonObject: JSONObject): Episode {
        jsonObject.let {
            val season = jsonObject.getInt(EpisodeEntry.SEASON)
            val episode = jsonObject.getInt(EpisodeEntry.EPISODE)
            val name = jsonObject.getString(EpisodeEntry.NAME)
            val airDate = jsonObject.getString(EpisodeEntry.AIR_DATE)
            return Episode(season, episode, name, airDate)
        }

    }
}
