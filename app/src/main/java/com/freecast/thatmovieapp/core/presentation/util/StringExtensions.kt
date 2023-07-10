package com.freecast.thatmovieapp.core.presentation.util

import com.freecast.thatmovieapp.BuildConfig

fun String?.toImageUrl(): String {
    return "${BuildConfig.IMAGE_BASE_URL}$this"
}