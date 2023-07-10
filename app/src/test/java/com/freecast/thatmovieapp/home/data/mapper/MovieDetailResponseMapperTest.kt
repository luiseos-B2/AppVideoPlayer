package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GenreResponse
import com.freecast.thatmovieapp.home.data.models.MovieDetailsResponse
import com.freecast.thatmovieapp.home.domain.model.Genre
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import org.junit.Test
import kotlin.test.assertEquals

class MovieDetailResponseMapperTest {

    private val fakeMovieDetailResponse = MovieDetailsResponse(
        id = 1,
        title = "Movie 1",
        posterPath = "posterPath 1",
        overview = "overview 1",
        releaseDate = "releaseDate 1",
        rating = 1.0F,
        genres = arrayListOf(
            GenreResponse(
                id = 1,
                name = "Action"
            ),
            GenreResponse(
                id = 2,
                name = "Adventure"
            )
        ),
        runtime = 1,
        backdropPath = "backdropPath 1"
    )

    private val fakeMovieDetail = MediaDetails(
        id = 1,
        title = "Movie 1",
        posterPath = "posterPath 1",
        overview = "overview 1",
        releaseDate = "releaseDate 1",
        rating = 1.0F,
        genres = arrayListOf(
            Genre(
                id = 1,
                name = "Action"
            ),
            Genre(
                id = 2,
                name = "Adventure"
            )
        ),
        runtime = 1,
        backdropPath = "backdropPath 1"
    )

    @Test
    fun givenMovieDetailResponseWhenMapperToPresentationDataThenReturnMediaDetail() {
        val result = fakeMovieDetailResponse.toPresentationData()

        assertEquals(fakeMovieDetailResponse.id, result.id)
        assertEquals(fakeMovieDetailResponse.title, result.title)
        assertEquals(fakeMovieDetailResponse.posterPath, result.posterPath)
        assertEquals(fakeMovieDetailResponse.overview, result.overview)
        assertEquals(fakeMovieDetailResponse.releaseDate, result.releaseDate)
        assertEquals(fakeMovieDetailResponse.rating, result.rating)
        assertEquals(fakeMovieDetailResponse.genres?.size, result.genres?.size)
        assertEquals(fakeMovieDetailResponse.genres?.get(0)?.id, result.genres?.get(0)?.id)
        assertEquals(fakeMovieDetailResponse.genres?.get(1)?.name, result.genres?.get(1)?.name)
        assertEquals(fakeMovieDetailResponse.runtime, result.runtime)
        assertEquals(fakeMovieDetailResponse.backdropPath, result.backdropPath)
    }

    @Test
    fun givenMediaDetailWhenMapperToMovieDetailNetworkingDataThenReturnMovieDetailResponse() {
        val result = fakeMovieDetail.toMovieDetailNetworkingData()

        assertEquals(fakeMovieDetail.id, result.id)
        assertEquals(fakeMovieDetail.title, result.title)
        assertEquals(fakeMovieDetail.posterPath, result.posterPath)
        assertEquals(fakeMovieDetail.overview, result.overview)
        assertEquals(fakeMovieDetail.releaseDate, result.releaseDate)
        assertEquals(fakeMovieDetail.rating, result.rating)
        assertEquals(fakeMovieDetail.genres?.size, result.genres?.size)
        assertEquals(fakeMovieDetail.genres?.get(0)?.id, result.genres?.get(0)?.id)
        assertEquals(fakeMovieDetail.genres?.get(1)?.name, result.genres?.get(1)?.name)
        assertEquals(fakeMovieDetail.runtime, result.runtime)
        assertEquals(fakeMovieDetail.backdropPath, result.backdropPath)
    }

}
