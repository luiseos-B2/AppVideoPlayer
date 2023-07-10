package com.freecast.thatmovieapp.home.domain.usecase

import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import com.freecast.thatmovieapp.home.domain.repository.MediaRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
class GetDetailsSeriesUseCaseTest: BaseTest() {

    private val repository = mockk<MediaRepository>()

    private val useCase = GetDetailsSeriesUseCase(repository)

    @Test
    fun givenARepositoryWhenInvokeThenShouldCallRepositoryGetDetailsSeries() {
        runTest(testCoroutineDispatcher) {
            //given
            val id = Random.nextInt()
            val mediaDetailMock = mockk<MediaDetails>()
            coEvery { repository.getDetailsSeries(id) } returns mediaDetailMock

            //when
            val result = useCase.invoke(id)

            //then
            assertEquals(mediaDetailMock, result)
            coVerify(exactly = 1) { repository.getDetailsSeries(id) }
        }
    }

}