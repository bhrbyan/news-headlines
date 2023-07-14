package com.news.feature.source

import com.news.data.source.model.Source

sealed class SourceViewState {
    object Loading : SourceViewState()
    data class Success(val sources: List<Source>) : SourceViewState()

    object Empty : SourceViewState()
    data class Error(val exception: Throwable) : SourceViewState()
}