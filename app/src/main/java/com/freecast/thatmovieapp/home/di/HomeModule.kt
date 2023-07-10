package com.freecast.thatmovieapp.home.di

import MediaComparator
import com.freecast.thatmovieapp.home.data.paging.MediaPagingSource
import com.freecast.thatmovieapp.home.data.repository.MediaRepositoryImpl
import com.freecast.thatmovieapp.home.data.service.MediaService
import com.freecast.thatmovieapp.home.domain.repository.MediaRepository
import com.freecast.thatmovieapp.home.domain.usecase.*
import com.freecast.thatmovieapp.home.presentation.home.feature.movie.adapter.MovieAdapter
import com.freecast.thatmovieapp.home.presentation.home.feature.movie.viewmodel.MovieViewModel
import com.freecast.thatmovieapp.home.presentation.home.feature.series.adapter.SeriesAdapter
import com.freecast.thatmovieapp.home.presentation.home.feature.series.viewmodel.SeriesViewModel
import com.freecast.thatmovieapp.home.presentation.mediadetails.viewmodel.MediaDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {

    //data
    single { get<Retrofit>().create(MediaService::class.java) }
    single<MediaRepository>{ MediaRepositoryImpl(get()) }

    //domain
    single { GetDetailMovieUseCase(get()) }
    single { GetDetailsSeriesUseCase(get()) }
    single { GetSeriesByGenreUseCase(get()) }
    single { GetSeriesGenresUseCase(get()) }
    single { GetTopRatedMoviesUseCase(get()) }

    //presentation
    single { MovieAdapter(get()) }
    single { SeriesAdapter(get()) }
    factory { MediaComparator() }

    viewModel { MovieViewModel(get()) }
    viewModel { SeriesViewModel(get(), get()) }
    viewModel { MediaDetailViewModel(get(), get()) }
}