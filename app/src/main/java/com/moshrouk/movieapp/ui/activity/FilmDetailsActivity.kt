package com.moshrouk.movieapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.moshrouk.movieapp.MovieDetalis
import com.moshrouk.movieapp.R
import com.moshrouk.movieapp.adapter.TabAdapter
import com.moshrouk.movieapp.data.local.RoomBuilder
import com.moshrouk.movieapp.data.model.FavMovie
import com.moshrouk.movieapp.data.reset.RetrofitClient
import com.moshrouk.movieapp.helper.loadImage
import com.moshrouk.movieapp.ui.fragment.OverviewFragment
import com.moshrouk.movieapp.ui.fragment.ReviewsFragment
import com.moshrouk.movieapp.ui.fragment.VideoFragment
import kotlinx.android.synthetic.main.activity_film_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailsActivity : AppCompatActivity() {
    lateinit var movie: MovieDetalis
    lateinit var favMovie: List<FavMovie>
    var mIsFavorite : Boolean ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)
        initBackBtn()
        getMovieDetails()
    }

    fun getMovieDetails() {
        val movieId = intent.getIntExtra("movie_id", 0)
//        val m =RoomBuilder.getRoomDB(this).getMovieDao().getMovieById(movieId)
//        if (m.equals(null)) {
//            mIsFavorite = false
//        }
        RetrofitClient.retrofit.getMovieDetails(movieId).enqueue(object : Callback<MovieDetalis> {

            override fun onFailure(call: Call<MovieDetalis>, t: Throwable) {
                Toast.makeText(
                    this@FilmDetailsActivity, "Request failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<MovieDetalis>, response: Response<MovieDetalis>) {

                if (response.isSuccessful) {
                    img_poster_banner.loadImage(response.body()?.backdropPath ?: "", R.drawable.avatar)
                    img_movie_poster.loadImage(response.body()?.posterPath ?: "", R.drawable.avatar)
                    tv_movie_name.text = response.body()?.title
                    tv_movie_des.text = response.body()?.tagline
                    initViewPager(response.body()!!)
                    initShareBtn(response.body()!!)

                }
            }

        })


        btn_fav.setOnClickListener {
            if (::movie.isInitialized) {
                val movie = FavMovie(movie.id, movie.posterPath, movie.title, movie.overview)
                Runnable {
                    RoomBuilder.getRoomDB(this).getMovieDao()
                        .insertMovie(movie).run {btn_fav.setImageResource(R.drawable.ic_favorite_black_24dp) } }
                Toast.makeText(this, "Successfully added to favorites", Toast.LENGTH_SHORT).show();


            }

        }
    }

        private fun initBackBtn() {
        img_back.setOnClickListener { finish() }
    }

    private fun initShareBtn(movieDetails: MovieDetalis) {

        img_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${movieDetails.title}\n${movieDetails.tagline}")
                type = "text/plain"
            }
            startActivity(sendIntent)
        }
    }

    private fun initViewPager(movieDetails: MovieDetalis) {

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment(movieDetails))
        fragments.add(VideoFragment())
        fragments.add(ReviewsFragment(movieDetails))

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Videos")
        titles.add("Reviews")


        viewpag.adapter = TabAdapter(fragments, titles, supportFragmentManager)
        tab.setupWithViewPager(viewpag)
    }
}