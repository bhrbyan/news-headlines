package com.news.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.news.data.article.model.Article
import com.news.data.article.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {

    private lateinit var source: String

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    fun setSource(source: String) {
        this.source = source
    }

    fun getArticles(): Flow<PagingData<Article>> =
        getArticlesUseCase.invoke(
            query = query.value,
            source = source
        ).cachedIn(viewModelScope)


    fun onSearch(query: String) {
        _query.value = query
    }


}