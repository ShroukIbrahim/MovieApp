package com.moshrouk.movieapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.moshrouk.movieapp.Movie
import com.moshrouk.movieapp.R
import com.moshrouk.movieapp.adapter.MovieAdapter
import com.moshrouk.movieapp.data.reset.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllMovies()
        search.setOnClickListener(View.OnClickListener {
            searchMovie()
        })

    }

    val openFilm = object : OpenFilm {
        override fun openFilm(id: Int) {
            val intent = Intent(this@MainActivity, FilmDetailsActivity::class.java)
            intent.putExtra("movie_id", id)
            startActivity(intent)

        }
    }
    fun getAllMovies() {
        RetrofitClient.retrofit.getMovies()
            .enqueue(object : Callback<Movie> {

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity, "Request failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                    if (response.isSuccessful) {
                        recycler_movies.adapter = MovieAdapter(response.body()!!.results, openFilm)
                        recycler_movies.layoutManager = LinearLayoutManager(this@MainActivity)
                    }

                }

            })


    }


    fun searchMovie(){
        RetrofitClient.retrofit.searchMovies("cc03e7435dd83a51086cbe3932f6663c","en-US",search_keyword.text.toString())
            .enqueue(object : Callback<Movie> {

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity, "Request failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                    if (response.isSuccessful) {
                        recycler_movies.clearOnChildAttachStateChangeListeners()
                        recycler_movies.adapter = MovieAdapter(response.body()!!.results, openFilm)
                        recycler_movies.layoutManager = LinearLayoutManager(this@MainActivity)
                    }

                }

            })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.menu_fav) {
            val intent = Intent(this, FavoriteMoviesActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    interface OpenFilm{
        fun openFilm(id: Int)
    }
}
