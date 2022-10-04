package com.lyft.android.ohmymovies.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyft.android.ohmymovies.R
import com.lyft.android.ohmymovies.domain.ObserveMoviesSectionsUseCase
import com.lyft.android.ohmymovies.domain.SyncMoviesUseCase
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
    private val observeMoviesSectionsUseCase: ObserveMoviesSectionsUseCase,
    private val syncMoviesUseCase: SyncMoviesUseCase
) : ViewModel() {

    private val _moviesHomeStateFlow = MutableStateFlow(MoviesHomeUiState())
    val moviesHomeStateFlow = _moviesHomeStateFlow.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onErrorOccurred(throwable)
    }

    init {
        syncMovies()
        observeMoviesSections()
    }

    private fun observeMoviesSections() {
        viewModelScope.launch(exceptionHandler) {
            observeMoviesSectionsUseCase().collect { sections ->
                _moviesHomeStateFlow.update {
                    it.copy(sections = sections)
                }
            }
        }
    }

    fun syncMovies() {
        if (_moviesHomeStateFlow.value.isLoading) {
            return
        }
        viewModelScope.launch(exceptionHandler) {
            _moviesHomeStateFlow.update { it.copy(isLoading = true) }
            syncMoviesUseCase()
            _moviesHomeStateFlow.update { it.copy(isLoading = false) }
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