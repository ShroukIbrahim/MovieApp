package com.moshrouk.movieapp.data.reset

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    val API_KEY: String = "cc03e7435dd83a51086cbe3932f6663c"
    val Base_Url :String ="https://api.themoviedb.org/3/"


    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val retrofit = retrofitBuilder.create<ApiService>()


}
