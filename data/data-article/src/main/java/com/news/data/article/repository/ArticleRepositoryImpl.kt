package com.news.data.article.repository

import com.news.data.article.model.Article
import com.news.data.article.service.ArticleService
import com.news.network.dispatcher.AppDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleService: ArticleService,
    private val appDispatcher: AppDispatcher
) : ArticleRepository {

    override suspend fun getArticles(source: String): Flow<List<Article>?> {
        return flow {
            val response = articleService.getArticles(source)
            if (response.isSuccessful) {
                val articles = response.body()?.articles?.map {
                    Article(
                        it.author,
                        it.title,
                        it.description,
                        it.url,
                        it.urlToImage,
                        it.publishedAt,
                        it.content
                    )
                }

                emit(articles)
            } else {
                emit(null)
            }
        }.flowOn(appDispatcher.io)
    }
}