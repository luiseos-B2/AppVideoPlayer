package com.freecast.thatmovieapp.home.presentation.home.feature.movie.viewmodel

import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.usecase.GetTopRatedMoviesUseCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelTest: BaseTest() {

    private val getTopRatedMoviesUseCase = mockk<GetTopRatedMoviesUseCase>()
    private lateinit var topRatedMoviesLiveDataMock: Observer<Resource<PagingData<Media>>>
    private val viewModel: MovieViewModel = MovieViewModel(getTopRatedMoviesUseCase)

    @Before
    override fun setUp() {
        super.setUp()
        topRatedMoviesLiveDataMock = mockk(relaxed = true)
        viewModel.topRatedMoviesLiveData.observeForever(topRatedMoviesLiveDataMock)
    }

    @Test
    fun givenATopRatedMoviesUseCaseWhenInvokeThenShouldReturningSuccess() =
        runTest(testCoroutineDispatcher) {
            // given
            val pagingDataMock = mockk<PagingData<Media>>()
            val resourceSlot = slot<Resource<PagingData<Media>>>()
            val flow = flowOf(pagingDataMock)
            coEvery { getTopRatedMoviesUseCase.invoke() } returns flow
            every { topRatedMoviesLiveDataMock.onChanged(capture(resourceSlot)) } just Runs

            // when
            viewModel.getTopRatedMovies()

            // then
            coVerify(exactly = 1) { getTopRatedMoviesUseCase.invoke() }
            assertEquals(viewModel.topRatedMoviesLiveData.value, resourceSlot.captured)
        }

    @Test
    fun givenATopRatedMoviesUseCaseWhenInvokeThenShouldReturningError() =
        runTest(testCoroutineDispatcher) {
            // given
            val errorMock = mockk<Throwable>()
            val resourceSlot = slot<Resource<PagingData<Media>>>()
            coEvery { getTopRatedMoviesUseCase.invoke() } throws errorMock
            every { topRatedMoviesLiveDataMock.onChanged(capture(resourceSlot)) } just Runs

            // when
            viewModel.getTopRatedMovies()

            // then
            coVerify(exactly = 1) { getTopRatedMoviesUseCase.invoke() }
            assertEquals(viewModel.topRatedMoviesLiveData.value, resourceSlot.captured)
        }
}