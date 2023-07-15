package com.news.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.news.data.article.model.Article
import com.news.data.article.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {

    fun getArticles(source: String): Flow<PagingData<Article>> =
        getArticlesUseCase.invoke(source).cachedIn(viewModelScope)

}