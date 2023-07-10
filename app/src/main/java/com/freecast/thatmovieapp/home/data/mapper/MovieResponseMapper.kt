package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.MovieResponse
import com.freecast.thatmovieapp.home.domain.model.Media

fun MovieResponse.toPresentationData() = Media(
    id = id,
    title = title,
    posterPath = posterPath
)

fun Media.toMovieNetworkingData() = MovieResponse(
    id = id,
    title = title,
    posterPath = posterPath
)