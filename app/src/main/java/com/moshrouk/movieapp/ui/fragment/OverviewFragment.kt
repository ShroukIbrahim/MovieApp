package com.moshrouk.movieapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moshrouk.movieapp.MovieDetalis
import com.moshrouk.movieapp.R
import kotlinx.android.synthetic.main.fragment_over_view.view.*


class OverviewFragment : Fragment {

    lateinit var movieDetails: MovieDetalis

    constructor() : super()
    constructor(movieDetails: MovieDetalis) {
        this.movieDetails = movieDetails
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_over_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.date.text = movieDetails.releaseDate
        view.rate.rating = movieDetails.voteAverage.toFloat()
        view.txt_rate.text = "${movieDetails.voteAverage}/10"
        view.overview_des.text = movieDetails.overview
    }




}
