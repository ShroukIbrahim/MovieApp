package com.moshrouk.movieapp.ui.activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.moshrouk.movieapp.R
import com.moshrouk.movieapp.adapter.FavAdapter
import com.moshrouk.movieapp.data.local.RoomBuilder
import kotlinx.android.synthetic.main.activity_favorite_movies.*
import com.moshrouk.movieapp.data.local.MovieDB


class FavoriteMoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)

        RoomBuilder.getRoomDB(this).getMovieDao().getAllMovies()
            .observe(this, Observer {

                if (it.isNotEmpty()) {
                    recycler_fav_movies.visibility = View.VISIBLE
                    tv_no_fav.visibility = View.GONE


                    recycler_fav_movies.adapter = FavAdapter(it)
                    recycler_fav_movies.layoutManager = LinearLayoutManager(this)
                }

            })
    }
}
