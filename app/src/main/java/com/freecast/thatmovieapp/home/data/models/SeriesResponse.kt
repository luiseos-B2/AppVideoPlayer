package com.freecast.thatmovieapp.home.data.models

import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("poster_path") val posterPath: String?
)
