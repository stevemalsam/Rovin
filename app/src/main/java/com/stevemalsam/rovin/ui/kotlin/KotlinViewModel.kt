package com.stevemalsam.rovin.ui.kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.network.HttpException
import com.stevemalsam.rovin.network.MarsApi.retrofitService
import com.stevemalsam.rovin.network.models.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException

sealed interface UIState {
    object Loading : UIState
    object Error : UIState
    data class Success(val photos: List<Photo>): UIState
}

enum class SortOrder {
    ASCENDING,
    DESCENDING
}

class KotlinViewModel : ViewModel() {
    private var _sortOrder = SortOrder.ASCENDING
    private var _photos: List<Photo> = emptyList()

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    fun getCuriosityPhotos(sol: Int, page: Int) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            _uiState.value = try {
                _photos = retrofitService.getPhotos(sol, page).photos
                UIState.Success( if(_sortOrder == SortOrder.ASCENDING) { _photos.sortedBy {it.id} } else { _photos.sortedByDescending {it.id} })
            } catch (e: IOException) {
                UIState.Error
            } catch (e: HttpException) {
                UIState.Error
            }
        }
    }

    init {
        getCuriosityPhotos(1000, 2)
    }

    fun setSortOrder(sortOrder: SortOrder) {
        if(sortOrder == _sortOrder) {
            return
        }
        _sortOrder = sortOrder
        _photos = when(sortOrder) {
            SortOrder.ASCENDING -> _photos.sortedBy { it.id }
            SortOrder.DESCENDING -> _photos.sortedByDescending { it.id }
        }

        if(_uiState.value is UIState.Success) {
            _uiState.update { UIState.Success(_photos) }
        }
    }
}