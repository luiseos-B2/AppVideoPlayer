package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.SeriesDetailResponse
import com.freecast.thatmovieapp.home.domain.model.MediaDetails

fun SeriesDetailResponse.toPresentationData() = MediaDetails(
    id = id,
    title = name,
    posterPath = posterPath,
    overview = overview,
    genres = genres?.map { it.toPresentationData() },
    backdropPath = backdropPath,
    rating = rating,
    releaseDate = releaseDate,
    runtime = runtime
)

fun MediaDetails.toSeriesDetailNetworkingData() = SeriesDetailResponse(
    id = id,
    name = title,
    posterPath = posterPath,
    overview = overview,
    genres = genres?.map { it.toNetworkingData() },
    backdropPath = backdropPath,
    rating = rating,
    releaseDate = releaseDate,
    runtime = runtime
)