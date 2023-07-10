package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GenreResponse
import com.freecast.thatmovieapp.home.domain.model.Genre
import org.junit.Test
import kotlin.test.assertEquals

class GenreResponseMapperTest {

    private val fakeGenreResponse = GenreResponse(
        id = 1,
        name = "Action"
    )

    private val fakeGenre = Genre(
        id = 1,
        name = "Action"
    )

    @Test
    fun givenGenreResponseWhenMapperToPresentationDataThenReturnGenre() {
        val result = fakeGenreResponse.toPresentationData()

        assertEquals(fakeGenre.id, result.id)
        assertEquals(fakeGenre.name, result.name)
    }

    @Test
    fun givenGenreWhenMapperToNetworkingDataThenReturnGenreResponse() {
        val result = fakeGenre.toNetworkingData()

        assertEquals(fakeGenre.id, result.id)
        assertEquals(fakeGenre.name, result.name)
    }

}