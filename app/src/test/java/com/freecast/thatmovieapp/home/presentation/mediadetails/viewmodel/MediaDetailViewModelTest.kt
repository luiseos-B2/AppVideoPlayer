package com.freecast.thatmovieapp.home.presentation.mediadetails.viewmodel

import androidx.lifecycle.Observer
import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import com.freecast.thatmovieapp.home.domain.usecase.GetDetailMovieUseCase
import com.freecast.thatmovieapp.home.domain.usecase.GetDetailsSeriesUseCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MediaDetailViewModelTest: BaseTest() {

    private val getDetailMoviesUseCase = mockk<GetDetailMovieUseCase>()
    private val getDetailSeriesUseCase = mockk<GetDetailsSeriesUseCase>()
    private lateinit var detailMediaLiveDataObserverMock: Observer<Resource<MediaDetails>>
    private val viewModel =  MediaDetailViewModel(getDetailMoviesUseCase, getDetailSeriesUseCase)

    @Before
    override fun setUp() {
        super.setUp()
        detailMediaLiveDataObserverMock = mockk(relaxed = true)
        viewModel.detailMediaLiveData.observeForever(detailMediaLiveDataObserverMock)
    }

    @Test
    fun givenADetailMoviesUseCaseWhenInvokeThenShouldReturningSuccess() {
        runTest(testCoroutineDispatcher) {
            //given
            val id = 1
            val mediaDetails = mockk<MediaDetails>()
            val resourceSlot = slot<Resource<MediaDetails>>()
            coEvery { getDetailMoviesUseCase.invoke(id) } returns mediaDetails
            every { detailMediaLiveDataObserverMock.onChanged(capture(resourceSlot)) } just Runs

            //when
            viewModel.getMovieDetail(id)

            //then
            coVerify(exactly = 1) { getDetailMoviesUseCase.invoke(id) }
            assertEquals(viewModel.detailMediaLiveData.value, resourceSlot.captured)
        }
    }

    @Test
    fun givenADetailMoviesUseCaseWhenInvokeThenShouldReturningError() {
        runTest(testCoroutineDispatcher) {
            //given
            val id = 1
            val error = mockk<Throwable>()
            val resourceSlot = slot<Resource<MediaDetails>>()
            coEvery { getDetailMoviesUseCase.invoke(id) } throws error
            every { detailMediaLiveDataObserverMock.onChanged(capture(resourceSlot)) } just Runs

            //when
            viewModel.getMovieDetail(id)

            //then
            coVerify(exactly = 1) { getDetailMoviesUseCase.invoke(1) }
            assertEquals(viewModel.detailMediaLiveData.value, resourceSlot.captured)
        }
    }

    @Test
    fun givenADetailSeriesUseCaseWhenInvokeThenShouldReturningSuccess() {
        runTest(testCoroutineDispatcher) {
            //given
            val id = 1
            val mediaDetails = mockk<MediaDetails>()
            val resourceSlot = slot<Resource<MediaDetails>>()
            coEvery { getDetailSeriesUseCase.invoke(id) } returns mediaDetails
            every { detailMediaLiveDataObserverMock.onChanged(capture(resourceSlot)) } just Runs

            //when
            viewModel.getSeriesDetail(id)

            //then
            coVerify(exactly = 1) { getDetailSeriesUseCase.invoke(1) }
            assertEquals(viewModel.detailMediaLiveData.value, resourceSlot.captured)
        }
    }

    @Test
    fun givenADetailSeriesUseCaseWhenInvokeThenShouldReturningError() {
        runTest(testCoroutineDispatcher) {
            //given
            val id = 1
            val mediaDetails = mockk<Throwable>()
            val resourceSlot = slot<Resource<MediaDetails>>()
            coEvery { getDetailSeriesUseCase.invoke(id) } throws  mediaDetails
            every { detailMediaLiveDataObserverMock.onChanged(capture(resourceSlot)) } just Runs

            //when
            viewModel.getSeriesDetail(id)

            //then
            coVerify(exactly = 1) { getDetailSeriesUseCase.invoke(1) }
            assertEquals(viewModel.detailMediaLiveData.value, resourceSlot.captured)
        }
    }
}