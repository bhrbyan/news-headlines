package com.news.feature.article

import com.news.data.article.model.Article

sealed class ArticleViewState {
    object Loading : ArticleViewState()
    data class Success(val sources: List<Article>) : ArticleViewState()

    object Empty : ArticleViewState()
    data class Error(val exception: Throwable) : ArticleViewState()
}