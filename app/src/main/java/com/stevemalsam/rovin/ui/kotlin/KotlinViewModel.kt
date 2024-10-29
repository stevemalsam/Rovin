package com.stevemalsam.rovin.ui.kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevemalsam.rovin.network.MarsApi
import kotlinx.coroutines.launch

class KotlinViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private fun getCuriosityPhotos(sol: Int, page: Int) {
        viewModelScope.launch {
            _text.value = MarsApi.retrofitService.getPhotos(sol, page).photos.count().toString()
        }
    }

    init {
        getCuriosityPhotos(1000, 2)
    }
}