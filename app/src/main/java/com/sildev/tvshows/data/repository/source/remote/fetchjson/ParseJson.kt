package com.sildev.tvshows.data.repository.source.remote.fetchjson

import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowEntry
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
}
