package com.freecast.thatmovieapp.home.data.models

import com.google.gson.annotations.SerializedName

class GetMoviesResponse (
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val movies: List<MovieResponse>,
    @SerializedName("total_pages") val pages: Int?
)
