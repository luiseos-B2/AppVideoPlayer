package com.freecast.thatmovieapp.home.domain.model

import java.io.Serializable

data class MediaDetails(
    val id: Int,
    val title: String?,
    val posterPath: String?,
    val overview: String?,
    val genres: List<Genre>?,
    val backdropPath: String?,
    val rating: Float?,
    val runtime: Int?,
    val releaseDate: String?
): Serializable
