package com.moshrouk.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class FavMovie(

    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "photo")
    val img: String,
    val title: String,
    val overview: String

)