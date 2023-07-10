package com.freecast.thatmovieapp.home.domain.model

import java.io.Serializable

data class Genre(
    val id: Int,
    val name: String?
): Serializable {
    override fun toString(): String {
        return name ?: ""
    }
}
