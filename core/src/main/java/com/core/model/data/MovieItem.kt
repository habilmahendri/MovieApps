package com.core.model.data

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("original_title")
    val originalTitle:String,
    @SerializedName("poster_path")
    val posterPath:String?,
    @SerializedName("backdrop_path")
    val backdrop_path:String,
    @SerializedName("release_date")
    val release_date:String,
    @SerializedName("overview")
    val overview:String,
    @SerializedName("id")
    val id:String,
)
