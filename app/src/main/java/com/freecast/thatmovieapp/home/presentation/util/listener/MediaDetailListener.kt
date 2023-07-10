package com.freecast.thatmovieapp.home.presentation.util.listener

import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.model.MediaType

interface MediaDetailListener {
    fun onDetailsMediaClicked(mediaType: MediaType, media: Media)
}