package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GetMoviesResponse
import com.freecast.thatmovieapp.home.data.models.MovieResponse
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.model.MediaResponse
import org.junit.Test
import kotlin.test.assertEquals

class GetMoviesResponseMapperTest {

    private val fakeGetMoviesResponse = GetMoviesResponse(
        page = 1,
        movies = listOf(
            MovieResponse(
                id = 1,
                title = "Movie 1",
                posterPath = "posterPath 1",
            ),
            MovieResponse(
                id = 2,
                title = "Movie 2",
                posterPath = "posterPath 2",
            )
        ),
        pages = 1
    )

    private val fakeMediaResponse = MediaResponse(
        page = 1,
        results = listOf(
            Media(
                id = 1,
                title = "Movie 1",
                posterPath = "posterPath 1",
            ),
            Media(
                id = 2,
                title = "Movie 2",
                posterPath = "posterPath 2",
            )
        ),
        totalPages = 1
    )

    @Test
    fun givenGetMoviesResponseWhenMapperToPresentationDataThenReturnMediaResponse() {
        val result  = fakeGetMoviesResponse.toPresentationData()

        assertEquals(fakeGetMoviesResponse.page, result.page)
        assertEquals(fakeGetMoviesResponse.movies.size, result.results.size)
        assertEquals(fakeGetMoviesResponse.movies[0].id, result.results[0].id)
        assertEquals(fakeGetMoviesResponse.movies[1].title, result.results[1].title)
        assertEquals(fakeGetMoviesResponse.pages, result.totalPages)
    }

    @Test
    fun givenMediaResponseWhenMapperToGetMoviesNetworkingDataThenReturnGetMoviesResponse() {
        val result  = fakeMediaResponse.toGetMoviesNetworkingData()

        assertEquals(fakeMediaResponse.page, result.page)
        assertEquals(fakeMediaResponse.results.size, result.movies.size)
        assertEquals(fakeMediaResponse.results[0].id, result.movies[0].id)
        assertEquals(fakeMediaResponse.results[1].title, result.movies[1].title)
        assertEquals(fakeMediaResponse.totalPages, result.pages)
    }

}