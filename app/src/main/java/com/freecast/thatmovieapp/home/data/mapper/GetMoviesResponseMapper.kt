package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GetMoviesResponse
import com.freecast.thatmovieapp.home.domain.model.MediaResponse

fun GetMoviesResponse.toPresentationData() = MediaResponse(
    page = page,
    results = movies.map { it.toPresentationData() },
    totalPages = pages
)

fun MediaResponse.toGetMoviesNetworkingData() = GetMoviesResponse(
    page = page,
    movies = results.map { it.toMovieNetworkingData() },
    pages = totalPages
)