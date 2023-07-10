package com.freecast.thatmovieapp.home.domain.usecase

import androidx.paging.PagingData
import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.repository.MediaRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetTopRatedMoviesUseCaseTest: BaseTest() {

    private val repository = mockk<MediaRepository>()

    private val useCase = GetTopRatedMoviesUseCase(repository)

    @Test
    fun givenARepositoryWhenInvokeThenShouldCallRepositoryGetTopRatedMovies() {
        //given
        val pagingDataMock = mockk<Flow<PagingData<Media>>>()
        coEvery { repository.getTopRatedMovies() } returns pagingDataMock

        //when
        val result = useCase.invoke()

        //then
        assertEquals(result, pagingDataMock)
        coVerify(exactly = 1) { repository.getTopRatedMovies() }
    }

}