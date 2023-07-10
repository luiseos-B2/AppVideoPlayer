package com.freecast.thatmovieapp.home.domain.usecase

import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import com.freecast.thatmovieapp.home.domain.repository.MediaRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
class GetDetailMovieUseCaseTest: BaseTest() {

    private val repository: MediaRepository = mockk()

    private val useCase = GetDetailMovieUseCase(repository)

    @Test
    fun givenARepositoryWhenInvokeThenShouldCallRepositoryGetDetailsMovie() {
        runTest(testCoroutineDispatcher) {
            //given
            val id = Random.nextInt()
            val mediaDetailMock = mockk<MediaDetails>()
            coEvery { repository.getDetailsMovie(id) } returns mediaDetailMock

            //when
            val result = useCase.invoke(id)

            //then
            assertEquals(mediaDetailMock, result)
            coVerify(exactly = 1) { repository.getDetailsMovie(id) }
        }
    }

}