package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GetSeriesResponse
import com.freecast.thatmovieapp.home.domain.model.MediaResponse

fun GetSeriesResponse.toPresentationData() = MediaResponse(
    page = page,
    results = series.map { it.toPresentationData() },
    totalPages = pages
)

fun MediaResponse.toGetSeriesNetworkingData() = GetSeriesResponse(
    page = page,
    series = results.map { it.toSeriesNetworkingData() },
    pages = totalPages
)