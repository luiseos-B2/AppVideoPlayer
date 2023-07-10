package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.SeriesResponse
import com.freecast.thatmovieapp.home.domain.model.Media

fun SeriesResponse.toPresentationData() = Media(
    id = id,
    title = name,
    posterPath = posterPath
)

fun Media.toSeriesNetworkingData() = SeriesResponse(
    id = id,
    name = title,
    posterPath = posterPath
)