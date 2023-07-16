package com.news.data.article.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.news.data.article.model.Article
import com.news.data.article.paging.ArticlePagingSource
import com.news.data.article.service.ArticleService
import com.news.network.config.ApiConfig
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleService: ArticleService
) : ArticleRepository {

    override fun getArticles(query: String?, source: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = ApiConfig.PAGE_SIZE),
            pagingSourceFactory = {
                ArticlePagingSource(query, source, articleService)
            }
        )
            .flow
    }
}