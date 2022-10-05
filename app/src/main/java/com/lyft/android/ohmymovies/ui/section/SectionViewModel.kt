package com.lyft.android.ohmymovies.ui.section

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lyft.android.ohmymovies.domain.ObservePagingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
    observePagingMoviesUseCase: ObservePagingMoviesUseCase
) : ViewModel() {
    val moviesPagingFlow = observePagingMoviesUseCase().cachedIn(viewModelScope)
}