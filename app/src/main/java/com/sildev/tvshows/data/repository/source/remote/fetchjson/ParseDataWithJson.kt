package com.sildev.tvshows.data.repository.source.remote.fetchjson

import com.sildev.tvshows.data.model.TVShowEntry
import org.json.JSONObject

class ParseDataWithJson {
    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String): Any {
        val data = mutableListOf<Any>()
        val jsonArray = jsonObject?.getJSONArray(keyEntity)
        for (i in 0 until (jsonArray?.length() ?: 0)) {
            val item = parseJsonToObject(jsonArray?.getJSONObject(i), keyEntity)
            if (item != null) {
                data.add(item)
            }
        }
        return data
    }

    private fun parseJsonToObject(jsonObject: JSONObject?, keyEntity: String): Any? {
        if (jsonObject != null) {
            return when (keyEntity) {
                TVShowEntry.TV_SHOWS -> ParseJson().tvShowParseJson(jsonObject)
                else -> null
            }
        }
        return null
    }
}
