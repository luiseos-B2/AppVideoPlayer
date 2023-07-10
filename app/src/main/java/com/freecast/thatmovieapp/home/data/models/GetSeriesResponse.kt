package com.freecast.thatmovieapp.home.data.models

import com.google.gson.annotations.SerializedName

data class GetSeriesResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val series: List<SeriesResponse>,
    @SerializedName("total_pages") val pages: Int?
)