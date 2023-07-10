package com.freecast.thatmovieapp.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.freecast.thatmovieapp.home.data.mapper.toPresentationData
import com.freecast.thatmovieapp.home.data.paging.MediaDataSource
import com.freecast.thatmovieapp.home.data.paging.MediaPagingSource
import com.freecast.thatmovieapp.home.data.service.MediaService
import com.freecast.thatmovieapp.home.domain.model.Genres
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import com.freecast.thatmovieapp.home.domain.repository.MediaRepository
import com.freecast.thatmovieapp.home.domain.model.MediaType
import kotlinx.coroutines.flow.Flow

class MediaRepositoryImpl(
    private val mediaService: MediaService,
): MediaRepository {
    override fun getTopRatedMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                MediaPagingSource(
                    MediaType.MOVIE,
                    MediaDataSource(mediaService)
                )
            }
        ).flow
    }

    override suspend fun getDetailsMovie(id: Int): MediaDetails {
        return mediaService.getDetailsMovie(id).toPresentationData()
    }

    override suspend fun getSeriesGenres(): Genres {
        return mediaService.getSeriesGenres().toPresentationData()
    }

    override fun getSeriesByGenre(id: Int): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                MediaPagingSource(
                    MediaType.SERIES,
                    MediaDataSource(mediaService),
                    id
                )
            }
        ).flow
    }

    override suspend fun getDetailsSeries(id: Int): MediaDetails {
        return mediaService.getDetailsSeries(id).toPresentationData()
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}