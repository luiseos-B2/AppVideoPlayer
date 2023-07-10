package com.freecast.thatmovieapp.home.domain.model

import java.io.Serializable

data class Media(
    val id: Int,
    val title: String?,
    val posterPath: String?
): Serializable
