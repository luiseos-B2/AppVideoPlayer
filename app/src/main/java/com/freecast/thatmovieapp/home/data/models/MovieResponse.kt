package com.freecast.thatmovieapp.home.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterPath: String?
)