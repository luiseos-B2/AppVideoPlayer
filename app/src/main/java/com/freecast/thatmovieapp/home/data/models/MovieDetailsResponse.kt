package com.freecast.thatmovieapp.home.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val rating: Float?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("genres") val genres: List<GenreResponse>?,
    @SerializedName("runtime") val runtime: Int?
)
