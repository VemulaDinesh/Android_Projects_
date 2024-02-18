package com.example.multirecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    private val _mainData = MutableLiveData<MainData>()
    val mainData: LiveData<MainData> = _mainData
    private val _mediaData = MutableLiveData<MediaResponse>()
    val mediaData: LiveData<MediaResponse> = _mediaData
    fun getSections() {
        viewModelScope.launch {
            _mainData.value = repository.getSections()
        }
    }
    fun getMediaDetails(id: Int) {
        viewModelScope.launch {
            _mediaData.value = repository.getMediaDetails(id)
        }
    }
}
