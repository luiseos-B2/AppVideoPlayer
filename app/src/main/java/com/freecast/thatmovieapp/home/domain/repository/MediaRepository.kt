package com.freecast.thatmovieapp.home.domain.repository

import androidx.paging.PagingData
import com.freecast.thatmovieapp.home.domain.model.Genres
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    fun getTopRatedMovies(): Flow<PagingData<Media>>

    suspend fun getDetailsMovie(id: Int): MediaDetails

    suspend fun getSeriesGenres(): Genres

    fun getSeriesByGenre(id: Int): Flow<PagingData<Media>>

    suspend fun getDetailsSeries(id: Int): MediaDetails

}
