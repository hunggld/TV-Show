package com.sildev.tvshows.data.repository.source.local.sqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.sildev.tvshows.data.model.TVShow
import com.sildev.tvshows.data.model.TVShowEntry
import com.sildev.tvshows.data.repository.source.local.sqlite.DBHelper.Companion.TABLE_NAME

class TvShowDAOImplement(dbHelper: DBHelper) : TvShowDAO {

    private var sqLiteDatabase: SQLiteDatabase? = null

    init {
        sqLiteDatabase = dbHelper.writableDatabase
    }

    override fun get(sql: String): MutableList<TVShow> {
        val listResult = mutableListOf<TVShow>()
        val cursor = sqLiteDatabase?.rawQuery(sql, null)
        while (cursor?.moveToNext() == true) {
            cursor.apply {
                val id = getInt(getColumnIndex(TVShowEntry.ID))
                val name = getString(getColumnIndex(TVShowEntry.NAME))
                val network = getString(getColumnIndex(TVShowEntry.NETWORK))
                val country = getString(getColumnIndex(TVShowEntry.COUNTRY))
                val startDate = getString(getColumnIndex(TVShowEntry.START_DATE))
                val thumbnail = getString(getColumnIndex(TVShowEntry.THUMBNAIL))
                val status = getString(getColumnIndex(TVShowEntry.STATUS))
                listResult.add(TVShow(id, name, startDate, country, network, thumbnail, status))
            }
        }
        return listResult
    }

    override fun getAll(): MutableList<TVShow> {
        val sql = "SELECT * FROM $TABLE_NAME"
        return get(sql)
    }

    override fun checkTvShowExist(tvShow: TVShow): Boolean? {
        val sql = "SELECT * FROM $TABLE_NAME WHERE " + TVShowEntry.ID + " = " + tvShow.id.toString()
        val cursor = sqLiteDatabase?.rawQuery(sql, null)
        return cursor?.moveToFirst()
    }

    override fun insert(tvShow: TVShow): Long? {
        val contentValues = ContentValues()
        contentValues.put(TVShowEntry.ID, tvShow.id)
        contentValues.put(TVShowEntry.NAME, tvShow.name)
        contentValues.put(TVShowEntry.NETWORK, tvShow.network)
        contentValues.put(TVShowEntry.COUNTRY, tvShow.country)
        contentValues.put(TVShowEntry.START_DATE, tvShow.startDate)
        contentValues.put(TVShowEntry.THUMBNAIL, tvShow.thumbnail)
        contentValues.put(TVShowEntry.STATUS, tvShow.status)
        return sqLiteDatabase?.insert(TABLE_NAME, null, contentValues)
    }

    override fun update(tvShow: TVShow): Int? {
        val contentValues = ContentValues()
        contentValues.put(TVShowEntry.NAME, tvShow.name)
        contentValues.put(TVShowEntry.NETWORK, tvShow.network)
        contentValues.put(TVShowEntry.COUNTRY, tvShow.country)
        contentValues.put(TVShowEntry.START_DATE, tvShow.startDate)
        contentValues.put(TVShowEntry.THUMBNAIL, tvShow.thumbnail)
        contentValues.put(TVShowEntry.STATUS, tvShow.status)
        val whereClause = TVShowEntry.ID + "=" + tvShow.id
        return sqLiteDatabase?.update(TABLE_NAME, contentValues, whereClause, null)
    }

    override fun delete(tvShow: TVShow): Int? {
        val whereClause = TVShowEntry.ID + "=" + tvShow.id
        return sqLiteDatabase?.delete(TABLE_NAME, whereClause, null)
    }

    companion object {
        private var instance: TvShowDAOImplement? = null
        fun getInstance(dbHelper: DBHelper) = synchronized(this) {
            instance ?: TvShowDAOImplement(dbHelper).also { instance = it }
        }
    }
}
