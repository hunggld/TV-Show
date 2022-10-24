package com.sildev.tvshows.data.repository.source.local.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sildev.tvshows.data.model.TVShowEntry.COUNTRY
import com.sildev.tvshows.data.model.TVShowEntry.ID
import com.sildev.tvshows.data.model.TVShowEntry.NAME
import com.sildev.tvshows.data.model.TVShowEntry.NETWORK
import com.sildev.tvshows.data.model.TVShowEntry.START_DATE
import com.sildev.tvshows.data.model.TVShowEntry.STATUS
import com.sildev.tvshows.data.model.TVShowEntry.THUMBNAIL

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTvShowTable =
            ("CREATE TABLE " + TABLE_NAME +
                    "(" + ID + " INTEGER PRIMARY KEY, "
                    + NAME + " TEXT, " + START_DATE + " TEXT, "
                    + COUNTRY + " TEXT," + NETWORK + " TEXT,"
                    + THUMBNAIL + " TEXT," + STATUS + " TEXT)")
        p0?.execSQL(createTvShowTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTvShowTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(dropTvShowTable)
        onCreate(p0)
    }

    companion object {
        const val DB_NAME = "TVShow"
        const val DB_VERSION = 1
        const val TABLE_NAME = "TvShowFavourite"
        private var instance: DBHelper? = null
        fun getInstance(context: Context) = synchronized(this) {
            instance ?: DBHelper(context).also { instance = it }
        }
    }
}
