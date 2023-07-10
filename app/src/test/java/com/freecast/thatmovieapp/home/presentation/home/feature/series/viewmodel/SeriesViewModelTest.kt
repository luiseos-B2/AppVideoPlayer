package com.freecast.thatmovieapp.home.presentation.home.feature.series.viewmodel

import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.home.domain.model.Genres
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import com.freecast.thatmovieapp.home.domain.usecase.GetSeriesByGenreUseCase
import com.freecast.thatmovieapp.home.domain.usecase.GetSeriesGenresUseCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random


@OptIn(ExperimentalCoroutinesApi::class)
class SeriesViewModelTest: BaseTest() {

    private val getSeriesGenresUseCase = mockk<GetSeriesGenresUseCase>()
    private val getSeriesByGenreUseCase = mockk<GetSeriesByGenreUseCase>()
    private lateinit var genresLiveDataMock: Observer<Resource<Genres>>
    private lateinit var seriesLiveDataMockk: Observer<Resource<PagingData<Media>>>
    private val viewModel: SeriesViewModel = SeriesViewModel(getSeriesGenresUseCase, getSeriesByGenreUseCase)

    @Before
    override fun setUp() {
        super.setUp()
        genresLiveDataMock = mockk(relaxed = true)
        seriesLiveDataMockk = mockk(relaxed = true)
        viewModel.genresLiveData.observeForever(genresLiveDataMock)
        viewModel.seriesLiveData.observeForever(seriesLiveDataMockk)
    }

    @Test
    fun givenASeriesGenresUseCaseWhenInvokeThenShouldReturningSuccess() = runTest(testCoroutineDispatcher) {
        // given
        val genresMock = mockk<Genres>()
        val resourceSlot = slot<Resource<Genres>>()
        coEvery { getSeriesGenresUseCase.invoke() } returns genresMock
        every { genresLiveDataMock.onChanged(capture(resourceSlot)) } just Runs

        // when
        viewModel.getGenres()

        // then
        coVerify(exactly = 1) { getSeriesGenresUseCase.invoke() }
        assertEquals(viewModel.genresLiveData.value, resourceSlot.captured)
    }

    @Test
    fun givenASeriesGenresUseCaseWhenInvokeThenShouldReturningError() = runTest(testCoroutineDispatcher) {
        // given
        val errorMock = mockk<Throwable>()
        val resourceSlot = slot<Resource<Genres>>()
        coEvery { getSeriesGenresUseCase.invoke() } throws  errorMock
        every { genresLiveDataMock.onChanged(capture(resourceSlot)) } just Runs

        // when
        viewModel.getGenres()

        // then
        coVerify(exactly = 1) { getSeriesGenresUseCase.invoke() }
        assertEquals(viewModel.genresLiveData.value, resourceSlot.captured)
    }


    @Test
    fun givenASeriesByGenreUseCaseWhenInvokeThenShouldReturningSuccess() =
        runTest(testCoroutineDispatcher) {
            // given
            val genreId = 1
            val pagingDataMock = mockk<PagingData<Media>>()
            val resourceSlot = slot<Resource<PagingData<Media>>>()
            val flow = flowOf(pagingDataMock)
            coEvery { getSeriesByGenreUseCase.invoke(genreId) } returns flow
            every { seriesLiveDataMockk.onChanged(capture(resourceSlot)) } just Runs


            // when
            viewModel.getSeriesByGenre(genreId)

            // then
            coVerify(exactly = 1) { getSeriesByGenreUseCase.invoke(genreId) }
            assertEquals(viewModel.seriesLiveData.value, resourceSlot.captured)
        }

    @Test
    fun givenASeriesByGenreUseCaseWhenInvokeThenShouldReturningError() =
        runTest(testCoroutineDispatcher) {
            // given
            val genreId = 1
            val errorMock = mockk<Throwable>()
            val resourceSlot = slot<Resource<PagingData<Media>>>()
            coEvery { getSeriesByGenreUseCase.invoke(genreId) } throws  errorMock
            every { seriesLiveDataMockk.onChanged(capture(resourceSlot)) } just Runs


            // when
            viewModel.getSeriesByGenre(genreId)

            // then
            coVerify(exactly = 1) { getSeriesByGenreUseCase.invoke(genreId) }
            assertEquals(viewModel.seriesLiveData.value, resourceSlot.captured)
        }

}