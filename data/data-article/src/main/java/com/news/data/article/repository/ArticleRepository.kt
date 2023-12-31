package com.news.data.article.repository

import androidx.paging.PagingData
import com.news.data.article.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    fun getArticles(query: String?, source: String): Flow<PagingData<Article>>

}