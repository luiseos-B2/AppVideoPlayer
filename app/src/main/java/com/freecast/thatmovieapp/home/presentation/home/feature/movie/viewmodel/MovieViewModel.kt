package com.freecast.thatmovieapp.home.presentation.home.feature.movie.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.usecase.GetTopRatedMoviesUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getTopRatedMovies: GetTopRatedMoviesUseCase
): ViewModel() {

    private val _topRatedMoviesLiveData: MutableLiveData<Resource<PagingData<Media>>> = MutableLiveData()
    val topRatedMoviesLiveData: LiveData<Resource<PagingData<Media>>>
        get() = _topRatedMoviesLiveData

    fun getTopRatedMovies() {
        _topRatedMoviesLiveData.value = Resource.Loading()
        viewModelScope.launch {
            runCatching {
                getTopRatedMovies.invoke()
            }.onSuccess {
                it.cachedIn(viewModelScope).collectLatest { pagingData ->
                    _topRatedMoviesLiveData.value = Resource.Success(pagingData)
                }
            }.onFailure {
                _topRatedMoviesLiveData.value = Resource.Error(it.message)
            }
        }
    }
}