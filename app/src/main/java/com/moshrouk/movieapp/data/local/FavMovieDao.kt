package com.moshrouk.movieapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.moshrouk.movieapp.data.model.FavMovie

@Dao
interface FavMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg movie: FavMovie?)

//    @Update
//    fun updateMovie(movie : FavMovie)


    @Delete
    fun deleteMovie(movie: FavMovie)

@androidx.room.Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<FavMovie>>

    @androidx.room.Query("SELECT * FROM movies WHERE id=:movieId")
    fun getMovieById(movieId: Int): FavMovie


//    @Query("DELETE FROM movies")
//    fun deleteAllMovies()
}

