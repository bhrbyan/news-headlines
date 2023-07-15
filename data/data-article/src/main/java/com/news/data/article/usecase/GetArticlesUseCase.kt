package com.news.data.article.usecase

import androidx.paging.PagingData
import com.news.data.article.model.Article
import com.news.data.article.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val articleRepository: ArticleRepository) {

    operator fun invoke(source: String): Flow<PagingData<Article>> {
        return articleRepository.getArticles(source)
    }

}