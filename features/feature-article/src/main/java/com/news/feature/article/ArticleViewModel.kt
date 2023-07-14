package com.news.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.data.article.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {

    private val _viewState = MutableStateFlow<ArticleViewState>(ArticleViewState.Loading)
    val viewState: StateFlow<ArticleViewState> = _viewState.asStateFlow()

    fun getArticles(source: String) {
        viewModelScope.launch {
            getArticlesUseCase.invoke(source)
                .catch { e ->
                    _viewState.value = ArticleViewState.Error(e)
                }
                .collect { articles ->
                    if (articles?.isNotEmpty() == true) {
                        _viewState.value = ArticleViewState.Success(articles)
                    }
                }
        }
    }

}