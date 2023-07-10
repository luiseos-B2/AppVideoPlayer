package com.freecast.thatmovieapp.home.data.models

import com.google.gson.annotations.SerializedName

data class SeriesDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val rating: Float?,
    @SerializedName("first_air_date") val releaseDate: String?,
    @SerializedName("genres") val genres: List<GenreResponse>?,
    @SerializedName("movie_genres") val runtime: Int?
)
