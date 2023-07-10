package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.MovieDetailsResponse
import com.freecast.thatmovieapp.home.domain.model.MediaDetails

fun MovieDetailsResponse.toPresentationData() = MediaDetails(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    genres = genres?.map { it.toPresentationData() },
    backdropPath = backdropPath,
    rating = rating,
    releaseDate = releaseDate,
    runtime = runtime
)

fun MediaDetails.toMovieDetailNetworkingData() = MovieDetailsResponse(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    genres = genres?.map { it.toNetworkingData() },
    backdropPath = backdropPath,
    rating = rating,
    releaseDate = releaseDate,
    runtime = runtime
)