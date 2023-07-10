package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GenreResponse
import com.freecast.thatmovieapp.home.domain.model.Genre

fun GenreResponse.toPresentationData() = Genre(
    id = id,
    name = name
)

fun Genre.toNetworkingData() = GenreResponse(
    id = id,
    name = name
)