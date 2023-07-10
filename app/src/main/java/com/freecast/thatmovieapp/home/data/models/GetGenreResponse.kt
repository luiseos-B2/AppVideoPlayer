package com.freecast.thatmovieapp.home.data.models

import com.google.gson.annotations.SerializedName

data class GetGenreResponse(
    @SerializedName("genres") val genres: List<GenreResponse>?
)