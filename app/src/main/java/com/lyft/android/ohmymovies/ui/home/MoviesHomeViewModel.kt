package com.lyft.android.ohmymovies.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyft.android.ohmymovies.R
import com.lyft.android.ohmymovies.domain.GetMoviesSectionsUseCase
import com.lyft.android.ohmymovies.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val getMoviesSectionsUseCase: GetMoviesSectionsUseCase
) : ViewModel() {

    private val _moviesHomeStateFlow = MutableStateFlow(MoviesHomeUiState())
    val moviesHomeStateFlow = _moviesHomeStateFlow.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onErrorOccurred(throwable)
    }

    init {
        loadMoviesSections()
    }

    private fun loadMoviesSections() {
        viewModelScope.launch(exceptionHandler) {
            val sections = getMoviesSectionsUseCase()
            _moviesHomeStateFlow.update {
                it.copy(
                    isLoading = false,
                    sections = sections
                )
            }
        }
    }

    private fun onErrorOccurred(throwable: Throwable) {
        _moviesHomeStateFlow.update {
            it.copy(
                isLoading = false,
                errorMessage = UiText.StringResource(R.string.generic_error_message)
            )
        }
        Log.e("HOME", "Error occurred", throwable)
    }

    fun onErrorShown() {
        _moviesHomeStateFlow.update {
            it.copy(errorMessage = null)
        }
    }
}