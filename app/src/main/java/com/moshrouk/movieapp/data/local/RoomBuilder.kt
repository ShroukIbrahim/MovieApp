package com.moshrouk.movieapp.data.local

import android.content.Context
import androidx.room.Room


object RoomBuilder {

    fun getRoomDB(context: Context) = Room.databaseBuilder(context, MovieDB::class.java, "movie_db")
        .allowMainThreadQueries()
        .build()


}