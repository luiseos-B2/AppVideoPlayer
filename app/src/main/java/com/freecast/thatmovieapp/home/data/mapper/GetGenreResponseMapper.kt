package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GetGenreResponse
import com.freecast.thatmovieapp.home.domain.model.Genres

fun GetGenreResponse.toPresentationData() = Genres(
    genres = this.genres?.map { it.toPresentationData() }
)

fun Genres.toNetworkingData() = GetGenreResponse(
    genres = this.genres?.map { it.toNetworkingData() }
)