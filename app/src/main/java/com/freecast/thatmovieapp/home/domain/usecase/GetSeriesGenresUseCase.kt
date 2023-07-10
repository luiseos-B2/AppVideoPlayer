package com.freecast.thatmovieapp.home.domain.usecase

import com.freecast.thatmovieapp.home.domain.repository.MediaRepository

class GetSeriesGenresUseCase(
    private val repository: MediaRepository
) {
    suspend operator fun invoke() = repository.getSeriesGenres()
}