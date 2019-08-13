package com.moshrouk.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moshrouk.movieapp.R
import com.moshrouk.movieapp.data.model.Reviews
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(val list: List<Reviews.Result>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)

        return ReviewViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {

        holder.itemView.author.text = list[position].author
        holder.itemView.content.text = list[position].content
    }


    class ReviewViewHolder(view : View) : RecyclerView.ViewHolder(view)
}