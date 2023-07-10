package com.freecast.thatmovieapp.home.domain.model

data class MediaResponse(
    val page: Int?,
    val results: List<Media>,
    val totalPages: Int?,
)
