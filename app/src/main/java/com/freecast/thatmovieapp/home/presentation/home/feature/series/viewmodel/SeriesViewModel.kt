package com.freecast.thatmovieapp.home.presentation.home.feature.series.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.home.domain.model.Genres
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.usecase.GetSeriesByGenreUseCase
import com.freecast.thatmovieapp.home.domain.usecase.GetSeriesGenresUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SeriesViewModel(
    private val getSeriesGenres: GetSeriesGenresUseCase,
    private val getSeriesByGenre: GetSeriesByGenreUseCase
): ViewModel() {

    private val _genresLiveData: MutableLiveData<Resource<Genres>> = MutableLiveData()
    val genresLiveData: LiveData<Resource<Genres>>
        get() = _genresLiveData

    private val _seriesLiveData: MutableLiveData<Resource<PagingData<Media>>> = MutableLiveData()
    val seriesLiveData: LiveData<Resource<PagingData<Media>>>
        get() = _seriesLiveData

    fun getGenres() {
        _genresLiveData.value = Resource.Loading()
        viewModelScope.launch {
            runCatching {
                getSeriesGenres.invoke()
            }.onSuccess {
                _genresLiveData.value = Resource.Success(it)
            }.onFailure {
                _genresLiveData.value = Resource.Error(it.message)
            }
        }
    }

    fun getSeriesByGenre(genreId: Int) {
        _seriesLiveData.value = Resource.Loading()
        viewModelScope.launch {
            runCatching {
                getSeriesByGenre.invoke(genreId)
            }.onSuccess {
                it.cachedIn(viewModelScope).collectLatest { pagingData ->
                    _seriesLiveData.value = Resource.Success(pagingData)
                }
            }.onFailure {
                _seriesLiveData.value = Resource.Error(it.message)
            }
        }
    }
}
