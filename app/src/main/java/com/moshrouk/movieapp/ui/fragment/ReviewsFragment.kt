package com.moshrouk.movieapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.moshrouk.movieapp.Movie
import com.moshrouk.movieapp.MovieDetalis
import com.moshrouk.movieapp.R
import com.moshrouk.movieapp.adapter.MovieAdapter
import com.moshrouk.movieapp.adapter.ReviewAdapter
import com.moshrouk.movieapp.data.model.Reviews
import com.moshrouk.movieapp.data.reset.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_reviews.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class ReviewsFragment : Fragment {

    lateinit var movieDetails: MovieDetalis
    constructor() : super()
    constructor(movieDetails: MovieDetalis) {
        this.movieDetails = movieDetails
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getMoviesReviews()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }

    fun getMoviesReviews() {
        RetrofitClient.retrofit.getMovieReview(movieDetails.id)
            .enqueue(object : Callback<Reviews> {

                override fun onFailure(call: Call<Reviews>, t: Throwable) {
                    Toast.makeText(
                        activity, "Request failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<Reviews>, response: Response<Reviews>) {

                    if (response.isSuccessful) {
                        reviews_list.adapter = ReviewAdapter(response.body()!!.results)
                        reviews_list.layoutManager = LinearLayoutManager(activity)
                    }

                }

            })


    }



}
