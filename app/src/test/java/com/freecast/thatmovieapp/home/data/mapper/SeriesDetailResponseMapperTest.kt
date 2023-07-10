package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GenreResponse
import com.freecast.thatmovieapp.home.data.models.SeriesDetailResponse
import com.freecast.thatmovieapp.home.domain.model.Genre
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import org.junit.Test
import kotlin.test.assertEquals

class SeriesDetailResponseMapperTest {

    private val fakeSeriesDetailResponse = SeriesDetailResponse(
        id = 1,
        name = "Series 1",
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

    private val fakeSeriesDetail = MediaDetails(
        id = 1,
        title = "Series 1",
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
    fun givenSeriesDetailResponseWhenMapperToPresentationDataThenReturnMediaDetail() {
        val result = fakeSeriesDetailResponse.toPresentationData()

        assertEquals(fakeSeriesDetailResponse.id, result.id)
        assertEquals(fakeSeriesDetailResponse.name, result.title)
        assertEquals(fakeSeriesDetailResponse.posterPath, result.posterPath)
        assertEquals(fakeSeriesDetailResponse.overview, result.overview)
        assertEquals(fakeSeriesDetailResponse.releaseDate, result.releaseDate)
        assertEquals(fakeSeriesDetailResponse.rating, result.rating)
        assertEquals(fakeSeriesDetailResponse.genres?.size, result.genres?.size)
        assertEquals(fakeSeriesDetailResponse.genres?.get(0)?.id, result.genres?.get(0)?.id)
        assertEquals(fakeSeriesDetailResponse.genres?.get(1)?.name, result.genres?.get(1)?.name)
        assertEquals(fakeSeriesDetailResponse.runtime, result.runtime)
        assertEquals(fakeSeriesDetailResponse.backdropPath, result.backdropPath)
    }

    @Test
    fun givenMediaDetailWhenMapperToSeriesDetailNetworkingDataThenReturnSeriesDetailResponse() {
        val result = fakeSeriesDetail.toSeriesDetailNetworkingData()

        assertEquals(fakeSeriesDetail.id, result.id)
        assertEquals(fakeSeriesDetail.title, result.name)
        assertEquals(fakeSeriesDetail.posterPath, result.posterPath)
        assertEquals(fakeSeriesDetail.overview, result.overview)
        assertEquals(fakeSeriesDetail.releaseDate, result.releaseDate)
        assertEquals(fakeSeriesDetail.rating, result.rating)
        assertEquals(fakeSeriesDetail.genres?.size, result.genres?.size)
        assertEquals(fakeSeriesDetail.genres?.get(0)?.id, result.genres?.get(0)?.id)
        assertEquals(fakeSeriesDetail.genres?.get(1)?.name, result.genres?.get(1)?.name)
        assertEquals(fakeSeriesDetail.runtime, result.runtime)
        assertEquals(fakeSeriesDetail.backdropPath, result.backdropPath)
    }

}
