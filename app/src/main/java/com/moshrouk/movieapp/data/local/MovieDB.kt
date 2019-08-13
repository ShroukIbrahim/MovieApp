package com.moshrouk.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moshrouk.movieapp.data.model.FavMovie

@Database(entities = [FavMovie::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {

    abstract fun getMovieDao(): FavMovieDao
}