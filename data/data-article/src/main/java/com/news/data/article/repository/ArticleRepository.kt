package com.news.data.article.repository

import com.news.data.article.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun getArticles(source: String): Flow<List<Article>?>

}