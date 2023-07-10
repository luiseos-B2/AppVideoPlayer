package com.freecast.thatmovieapp.home.data.models

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name")val name: String?
)
