package com.freecast.thatmovieapp.home.domain.usecase

import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.home.domain.model.Genres
import com.freecast.thatmovieapp.home.domain.repository.MediaRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetSeriesGenresUseCaseTest: BaseTest() {

    private val repository = mockk<MediaRepository>()

    private val useCase = GetSeriesGenresUseCase(repository)

    @Test
    fun givenARepositoryWhenInvokeThenShouldCallRepositoryGetSeriesGenres() {
        runTest(testCoroutineDispatcher) {
            //given
            val genresMock = mockk<Genres>()
            coEvery { repository.getSeriesGenres() } returns genresMock

            //when
            val result = useCase.invoke()

            //then
            assertEquals(result, genresMock)
            coVerify(exactly = 1) { repository.getSeriesGenres() }
        }
    }

}