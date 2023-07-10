package com.freecast.thatmovieapp.home.data.paging

import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.home.data.mapper.toPresentationData
import com.freecast.thatmovieapp.home.data.models.GetMoviesResponse
import com.freecast.thatmovieapp.home.data.models.GetSeriesResponse
import com.freecast.thatmovieapp.home.data.service.MediaService
import com.freecast.thatmovieapp.home.domain.model.MediaType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MediaDataSourceTest: BaseTest() {

    private val mediaService = mockk<MediaService>()

    private val mediaDataSource = MediaDataSource(mediaService)

    @Test
    fun givenAMediaTypeMovieWhenGetMediaDataThenShouldCallMediaServiceGetTopRatedMoviesOnce() =
        runTest(testCoroutineDispatcher) {
            // given
            val mediaType = MediaType.MOVIE
            val nextPageNumber = 1
            val mediaId = null
            val returnResponse = MOCK_GET_MOVIES_RESPONSE
            coEvery { mediaService.getTopRatedMovies(page = nextPageNumber) } returns  returnResponse

            // when
            val result = mediaDataSource.getMediaData(mediaType, nextPageNumber, mediaId)

            // then
            coVerify(exactly = 1) { mediaService.getTopRatedMovies(page = nextPageNumber) }
            assertEquals(returnResponse.toPresentationData(), result)
        }


    @Test
    fun givenAMediaTypeSeriesWhenGetMediaDataThenShouldCallMediaServiceGetTopRatedMoviesOnce() =
        runTest(testCoroutineDispatcher) {
            // given
            val mediaType = MediaType.SERIES
            val nextPageNumber = 1
            val mediaId = 1
            val returnResponse = MOCK_GET_SERIES_RESPONSE
            coEvery { mediaService.getSeriesByGenre(page = nextPageNumber, id = mediaId) } returns  returnResponse

            // when
            val result = mediaDataSource.getMediaData(mediaType, nextPageNumber, mediaId)

            // then
            coVerify(exactly = 1) { mediaService.getSeriesByGenre(page = nextPageNumber, id = mediaId) }
            assertEquals(returnResponse.toPresentationData(), result)
        }

    companion object {
        private val MOCK_GET_MOVIES_RESPONSE = GetMoviesResponse(
            page = 1,
            movies = listOf(),
            pages = 1,
        )

        private val MOCK_GET_SERIES_RESPONSE = GetSeriesResponse(
            page = 1,
            series = listOf(),
            pages = 1,
        )
    }

}