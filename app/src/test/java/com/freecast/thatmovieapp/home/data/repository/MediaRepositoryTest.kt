package com.freecast.thatmovieapp.home.data.repository

import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.home.data.mapper.toPresentationData
import com.freecast.thatmovieapp.home.data.models.*
import com.freecast.thatmovieapp.home.data.service.MediaService
import com.freecast.thatmovieapp.home.domain.repository.MediaRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MediaRepositoryTest : BaseTest() {

    private val service: MediaService = mockk()

    private val repository: MediaRepository = MediaRepositoryImpl(
        service
    )

    @Test
    fun givenARepositoryWhenGetSeriesGenresThenReturnGenres() = runTest(testCoroutineDispatcher) {
        //given
        val mockResponse = MOCK_GENRES_RESPONSE
        coEvery { service.getSeriesGenres() } returns mockResponse

        //when
        val result = repository.getSeriesGenres()

        //then
        coVerify(exactly = 1) { service.getSeriesGenres() }
        assertEquals(mockResponse.toPresentationData(), result)
    }

    @Test
    fun givenARepositoryWhenGetDetailsMovieThenReturnMediaDetail() = runTest(testCoroutineDispatcher) {
        //given
        val id = 1
        val mockResponse = MOCK_MOVIE_DETAILS_RESPONSE
        coEvery { service.getDetailsMovie(id) } returns mockResponse

        //when
        val result = repository.getDetailsMovie(id)

        //then
        assertEquals(mockResponse.toPresentationData(), result)
        coVerify(exactly = 1) { service.getDetailsMovie(id) }
    }

    @Test
    fun givenARepositoryWhenGetDetailsSeriesThenReturnMediaDetail() = runTest(testCoroutineDispatcher) {
        //given
        val id = 1
        val mockResponse = MOCK_SERIES_DETAILS_RESPONSE
        coEvery { service.getDetailsSeries(id) } returns mockResponse

        //when
        val result = repository.getDetailsSeries(id)

        //then
        assertEquals(mockResponse.toPresentationData(), result)
        coVerify(exactly = 1) { service.getDetailsSeries(id) }
    }

    companion object {
        private val MOCK_GENRES_RESPONSE = GetGenreResponse(
            genres = listOf(
                GenreResponse(
                    id = 1,
                    name = "Action"
                )
            )
        )
        private val MOCK_SERIES_DETAILS_RESPONSE = SeriesDetailResponse(
            id = 0,
            name = "The Simpsons",
            overview = "The Simpsons",
            posterPath = "The Simpsons",
            backdropPath = "The Simpsons",
            genres = listOf(
                GenreResponse(
                    id = 1,
                    name = "Action"
                )
            ),
            rating = 1f,
            releaseDate = "The Simpsons",
            runtime = 1,
        )

        private val MOCK_MOVIE_DETAILS_RESPONSE = MovieDetailsResponse(
            id = 0,
            title = "The Simpsons",
            overview = "The Simpsons",
            posterPath = "The Simpsons",
            backdropPath = "The Simpsons",
            genres = listOf(
                GenreResponse(
                    id = 1,
                    name = "Action"
                )
            ),
            rating = 1f,
            releaseDate = "The Simpsons",
            runtime = 1,
        )
    }

}