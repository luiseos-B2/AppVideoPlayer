package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.MovieResponse
import com.freecast.thatmovieapp.home.domain.model.Media
import org.junit.Test
import kotlin.test.assertEquals

class MovieResponseMapperTest {

    private val fakeMovieResponse = MovieResponse(
        id = 1,
        title = "Movie 1",
        posterPath = "posterPath 1"
    )

    private val fakeMovie = Media(
        id = 1,
        title = "Movie 1",
        posterPath = "posterPath 1"
    )

    @Test
    fun givenMovieResponseWhenMapperToPresentationDataThenReturnMedia() {
        val result = fakeMovieResponse.toPresentationData()

        assertEquals(fakeMovieResponse.id, result.id)
        assertEquals(fakeMovieResponse.title, result.title)
        assertEquals(fakeMovieResponse.posterPath, result.posterPath)
    }

    @Test
    fun givenMediaWhenMapperToMovieNetworkingDataThenReturnMovieResponse() {
        val result = fakeMovie.toMovieNetworkingData()

        assertEquals(fakeMovie.id, result.id)
        assertEquals(fakeMovie.title, result.title)
        assertEquals(fakeMovie.posterPath, result.posterPath)
    }

}