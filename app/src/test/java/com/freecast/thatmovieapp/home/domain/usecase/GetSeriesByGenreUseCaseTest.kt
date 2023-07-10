package com.freecast.thatmovieapp.home.domain.usecase

import androidx.paging.PagingData
import com.freecast.thatmovieapp.BaseTest
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.repository.MediaRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetSeriesByGenreUseCaseTest: BaseTest() {

    private val repository = mockk<MediaRepository>()

    private val useCase = GetSeriesByGenreUseCase(repository)

    @Test
    fun givenARepositoryWhenInvokeThenShouldCallRepositoryGetSeriesByGenre() {
        //given
        val genreId = 1
        val pagingDataMock = mockk<Flow<PagingData<Media>>>()
        every { repository.getSeriesByGenre(genreId) } returns pagingDataMock

        //when
        val result = useCase.invoke(genreId)

        //then
        assertEquals(result, pagingDataMock)
        verify(exactly = 1) { repository.getSeriesByGenre(genreId) }
    }

}