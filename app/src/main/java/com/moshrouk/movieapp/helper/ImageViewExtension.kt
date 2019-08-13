package com.moshrouk.movieapp.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.loadImage(imagePath: String, placeHolder: Int) {


    val base_img_url = "https://image.tmdb.org/t/p/w500"
    Glide.with(this)
        .load("$base_img_url$imagePath")
        .centerCrop()
        .placeholder(placeHolder)
        .error(placeHolder)
        .fallback(placeHolder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}