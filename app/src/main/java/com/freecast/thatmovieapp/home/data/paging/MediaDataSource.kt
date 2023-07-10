package com.freecast.thatmovieapp.home.data.paging

import com.freecast.thatmovieapp.home.data.mapper.toPresentationData
import com.freecast.thatmovieapp.home.data.service.MediaService
import com.freecast.thatmovieapp.home.domain.model.MediaResponse
import com.freecast.thatmovieapp.home.domain.model.MediaType

class MediaDataSource(private val mediaService: MediaService) {
    suspend fun getMediaData(mediaType: MediaType, nextPageNumber: Int, mediaId: Int?) : MediaResponse {
        return when (mediaType) {
            MediaType.MOVIE -> {
                val response = mediaService.getTopRatedMovies(page = nextPageNumber)
                response.toPresentationData()
            }
            MediaType.SERIES -> {
                val response = mediaService.getSeriesByGenre(page = nextPageNumber, id = mediaId)
                response.toPresentationData()
            }
        }
    }
}