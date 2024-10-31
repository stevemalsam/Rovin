package com.stevemalsam.rovin.ui.kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevemalsam.rovin.network.models.Photo
import kotlinx.coroutines.launch

sealed class UIState {
    class Loading : UIState()
    class Error : UIState()
}

class KotlinViewModel : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>().apply {
        value = emptyList()
    }

    val photos: LiveData<List<Photo>> = _photos

    private fun getCuriosityPhotos(sol: Int, page: Int) {
        viewModelScope.launch {
            _photos.value = com.stevemalsam.rovin.network.MarsApi.retrofitService.getPhotos(sol, page).photos
        }
    }

    init {
        getCuriosityPhotos(1000, 2)
    }
}