package com.moshrouk.movieapp.data.reset

import com.moshrouk.movieapp.Movie
import com.moshrouk.movieapp.MovieDetalis
import com.moshrouk.movieapp.data.model.Reviews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("discover/movie")
    fun getMovies(@Query("api_key") apiKey: String = RetrofitClient.API_KEY): Call<Movie>

    @GET("search/movie")
    fun searchMovies(@Query ("api_key") apiKey: String = RetrofitClient.API_KEY
    ,@Query("language") language:String="en-US"
    ,@Query("query") query:String ) :Call<Movie>


    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = RetrofitClient.API_KEY
    ): Call<MovieDetalis>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReview(@Path("movie_id") id: Int, @Query("api_key") apiKey: String = RetrofitClient.API_KEY
    ): Call<Reviews>

}