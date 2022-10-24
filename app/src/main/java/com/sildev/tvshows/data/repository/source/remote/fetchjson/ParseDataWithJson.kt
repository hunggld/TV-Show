package com.sildev.tvshows.data.repository.source.remote.fetchjson

import com.sildev.tvshows.data.model.TVShowDetailEntry
import com.sildev.tvshows.data.model.TVShowEntry
import org.json.JSONObject

class ParseDataWithJson {

    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String, status: String): Any? {
        when (keyEntity) {
            TVShowEntry.TV_SHOWS -> {
                val data = mutableListOf<Any>()
                val jsonArray = jsonObject?.getJSONArray(keyEntity)
                for (i in 0 until (jsonArray?.length() ?: 0)) {
                    val item = parseJsonToObject(jsonArray?.getJSONObject(i), keyEntity)
                    val tvShowStatus = jsonArray?.getJSONObject(i)?.getString(TVShowEntry.STATUS)
                    if (item != null && tvShowStatus?.contains(status, true) == true) {
                        data.add(item)
                    }
                }
                return data
            }
            else -> {
                val json = jsonObject?.getJSONObject(keyEntity)
                return parseJsonToObject(json, keyEntity)
            }

        }
    }

    private fun parseJsonToObject(jsonObject: JSONObject?, keyEntity: String): Any? {
        if (jsonObject != null) {
            return when (keyEntity) {
                TVShowEntry.TV_SHOWS -> ParseJson().tvShowParseJson(jsonObject)
                TVShowDetailEntry.TV_SHOW_DETAIL -> ParseJson().tvShowDetailParseJson(jsonObject)
                else -> null
            }
        }
        return null
    }
}
