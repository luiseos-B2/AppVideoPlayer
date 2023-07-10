package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.SeriesResponse
import com.freecast.thatmovieapp.home.domain.model.Media
import org.junit.Test
import kotlin.test.assertEquals

class SeriesResponseMapperTest {

    private val fakeSeriesResponse = SeriesResponse(
        id = 1,
        name = "Series 1",
        posterPath = "posterPath 1"
    )

    private val fakeSeries = Media(
        id = 1,
        title = "Series 1",
        posterPath = "posterPath 1"
    )

    @Test
    fun givenSeriesResponseWhenMapperToPresentationDataThenReturnMedia() {
        val result = fakeSeriesResponse.toPresentationData()

        assertEquals(fakeSeriesResponse.id, result.id)
        assertEquals(fakeSeriesResponse.name, result.title)
        assertEquals(fakeSeriesResponse.posterPath, result.posterPath)
    }

    @Test
    fun givenMediaWhenMapperToMovieNetworkingDataThenReturnSeriesResponse() {
        val result = fakeSeries.toSeriesNetworkingData()

        assertEquals(fakeSeries.id, result.id)
        assertEquals(fakeSeries.title, result.name)
        assertEquals(fakeSeries.posterPath, result.posterPath)
    }

}