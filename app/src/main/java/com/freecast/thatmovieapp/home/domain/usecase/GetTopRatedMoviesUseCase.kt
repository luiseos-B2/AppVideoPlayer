package com.freecast.thatmovieapp.home.domain.usecase

import com.freecast.thatmovieapp.home.domain.repository.MediaRepository

class GetTopRatedMoviesUseCase(
    private val repository: MediaRepository
) {
    operator fun invoke() = repository.getTopRatedMovies()
}