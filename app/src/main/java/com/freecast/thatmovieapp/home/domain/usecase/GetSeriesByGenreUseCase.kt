package com.freecast.thatmovieapp.home.domain.usecase

import com.freecast.thatmovieapp.home.domain.repository.MediaRepository

class GetSeriesByGenreUseCase(
    private val repository: MediaRepository
) {
    operator fun invoke(genreId: Int) = repository.getSeriesByGenre(genreId)
}