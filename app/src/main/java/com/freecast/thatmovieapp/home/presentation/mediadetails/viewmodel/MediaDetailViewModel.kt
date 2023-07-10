package com.freecast.thatmovieapp.home.presentation.mediadetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import com.freecast.thatmovieapp.home.domain.usecase.GetDetailMovieUseCase
import com.freecast.thatmovieapp.home.domain.usecase.GetDetailsSeriesUseCase
import kotlinx.coroutines.launch

class MediaDetailViewModel(
    private val getDetailsMovieUseCase: GetDetailMovieUseCase,
    private val getDetailsSeriesUseCase: GetDetailsSeriesUseCase,
): ViewModel() {

    private val _detailMediaLiveData: MutableLiveData<Resource<MediaDetails>> = MutableLiveData()
    val detailMediaLiveData: MutableLiveData<Resource<MediaDetails>>
        get() = _detailMediaLiveData

    fun getMovieDetail(id: Int) {
        _detailMediaLiveData.value = Resource.Loading()
        viewModelScope.launch {
            runCatching {
                getDetailsMovieUseCase.invoke(id)
            }.onSuccess {
                _detailMediaLiveData.value = Resource.Success(it)
            }.onFailure {
                _detailMediaLiveData.value = Resource.Error(it.message)
            }
        }
    }

    fun getSeriesDetail(id: Int) {
        _detailMediaLiveData.value = Resource.Loading()
        viewModelScope.launch {
            runCatching {
                getDetailsSeriesUseCase.invoke(id)
            }.onSuccess {
                _detailMediaLiveData.value = Resource.Success(it)
            }.onFailure {
                _detailMediaLiveData.value = Resource.Error(it.message)
            }
        }
    }

}