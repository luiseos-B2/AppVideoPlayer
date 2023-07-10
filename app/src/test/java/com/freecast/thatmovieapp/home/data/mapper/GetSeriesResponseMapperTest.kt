package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GetSeriesResponse
import com.freecast.thatmovieapp.home.data.models.SeriesResponse
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.model.MediaResponse
import org.junit.Test
import kotlin.test.assertEquals

class GetSeriesResponseMapperTest {

    private val fakeGetMoviesResponse = GetSeriesResponse(
        page = 1,
        series = listOf(
            SeriesResponse(
                id = 1,
                name = "Movie 1",
                posterPath = "posterPath 1",
            ),
            SeriesResponse(
                id = 2,
                name = "Movie 2",
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
        assertEquals(fakeGetMoviesResponse.series.size, result.results.size)
        assertEquals(fakeGetMoviesResponse.series[0].id, result.results[0].id)
        assertEquals(fakeGetMoviesResponse.series[1].name, result.results[1].title)
        assertEquals(fakeGetMoviesResponse.pages, result.totalPages)
    }

    @Test
    fun givenMediaResponseWhenMapperToGetMoviesNetworkingDataThenReturnGetMoviesResponse() {
        val result  = fakeMediaResponse.toGetSeriesNetworkingData()

        assertEquals(fakeMediaResponse.page, result.page)
        assertEquals(fakeMediaResponse.results.size, result.series.size)
        assertEquals(fakeMediaResponse.results[0].id, result.series[0].id)
        assertEquals(fakeMediaResponse.results[1].title, result.series[1].name)
        assertEquals(fakeMediaResponse.totalPages, result.pages)
    }

}