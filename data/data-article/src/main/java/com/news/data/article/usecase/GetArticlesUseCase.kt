package com.news.data.article.usecase

import com.news.data.article.model.Article
import com.news.data.article.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val articleRepository: ArticleRepository) {

    suspend operator fun invoke(source: String): Flow<List<Article>?> {
        return articleRepository.getArticles(source)
    }

}