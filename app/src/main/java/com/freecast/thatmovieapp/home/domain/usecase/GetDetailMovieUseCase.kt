package com.freecast.thatmovieapp.home.domain.usecase

import com.freecast.thatmovieapp.home.domain.repository.MediaRepository

class GetDetailMovieUseCase(
    private val repository: MediaRepository
) {
    suspend operator fun invoke(id: Int) = repository.getDetailsMovie(id)
}