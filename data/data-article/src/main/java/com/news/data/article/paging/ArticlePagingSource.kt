package com.news.data.article.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.news.data.article.model.Article
import com.news.data.article.service.ArticleService

class ArticlePagingSource(
    private val query: String?,
    private val source: String,
    private val articleService: ArticleService
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {

            val page = params.key ?: 1
            val response = articleService.getArticles(
                query = query,
                sources = source,
                page = page
            )

            val articles = response.articles.map {
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

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}