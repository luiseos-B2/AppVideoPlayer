package com.freecast.thatmovieapp.home.data.mapper

import com.freecast.thatmovieapp.home.data.models.GenreResponse
import com.freecast.thatmovieapp.home.data.models.GetGenreResponse
import com.freecast.thatmovieapp.home.domain.model.Genre
import com.freecast.thatmovieapp.home.domain.model.Genres
import org.junit.Test
import kotlin.test.assertEquals

class GetGenreResponseMapperTest {

    private val fakeGetGenreResponse = GetGenreResponse(
        genres = arrayListOf(
            GenreResponse(
                id = 1,
                name = "Action"
            ),
            GenreResponse(
                id = 2,
                name = "Adventure"
            )
        )
    )

    private val fakeGenres = Genres(
        genres = arrayListOf(
            Genre(
                id = 1,
                name = "Action"
            ),
            Genre(
                id = 2,
                name = "Adventure"
            )
        )
    )

    @Test
    fun givenGetGenreResponseWhenMapperToPresentationDataThenReturnGenres() {
        val result = fakeGetGenreResponse.toPresentationData()

        assertEquals(fakeGetGenreResponse.genres?.size, result.genres?.size)
        assertEquals(fakeGetGenreResponse.genres?.get(0)?.id, result.genres?.get(0)?.id)
        assertEquals(fakeGetGenreResponse.genres?.get(1)?.name, result.genres?.get(1)?.name)
    }

    @Test
    fun givenGenresWhenMapperToNetworkingDataThenReturnGetGenreResponse() {
        val result = fakeGenres.toNetworkingData()

        assertEquals(fakeGenres.genres?.size, result.genres?.size)
        assertEquals(fakeGenres.genres?.get(0)?.id, result.genres?.get(0)?.id)
        assertEquals(fakeGenres.genres?.get(1)?.name, result.genres?.get(1)?.name)
    }

}