package com.sildev.tvshows.data.repository.source.local.sqlite

import com.sildev.tvshows.data.model.TVShow

interface TvShowDAO {
    fun get(sql: String): MutableList<TVShow>
    fun getAll(): MutableList<TVShow>
    fun update(tvShow: TVShow): Int?
    fun insert(tvShow: TVShow): Long?
    fun delete(tvShow: TVShow): Int?
    fun checkTvShowExist(tvShow: TVShow): Boolean?
}
