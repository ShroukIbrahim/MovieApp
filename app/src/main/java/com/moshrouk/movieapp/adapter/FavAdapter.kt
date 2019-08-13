package com.moshrouk.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moshrouk.movieapp.R
import com.moshrouk.movieapp.data.model.FavMovie
import com.moshrouk.movieapp.helper.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class FavAdapter(
    val list: List<FavMovie>
) : RecyclerView.Adapter<FavAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.itemView.tv_movie_title.text = list[position].title
        holder.itemView.tv_movie_description.text = list[position].overview
        holder.itemView.img_movie_poster.loadImage(list[position].img, R.drawable.avatar)


    }


    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
}