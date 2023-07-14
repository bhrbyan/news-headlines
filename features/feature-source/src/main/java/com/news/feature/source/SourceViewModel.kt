package com.news.feature.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.data.source.usecase.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private val getSourcesUseCase: GetSourcesUseCase) :
    ViewModel() {

    private val _viewState: MutableStateFlow<SourceViewState> =
        MutableStateFlow(SourceViewState.Loading)
    val viewState: StateFlow<SourceViewState> = _viewState.asStateFlow()

    fun getSources(category: String) {
        viewModelScope.launch {
            getSourcesUseCase.invoke(category)
                .catch { e ->
                    _viewState.value = SourceViewState.Error(e)
                }
                .collect { sources ->
                    if (sources?.isNotEmpty() == true) {
                        _viewState.value = SourceViewState.Success(sources)
                    } else {
                        _viewState.value = SourceViewState.Empty
                    }
                }
        }
    }

}