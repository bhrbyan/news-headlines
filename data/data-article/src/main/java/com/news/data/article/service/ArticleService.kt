package com.news.data.article.service

import com.news.data.article.model.ArticleResponse
import com.news.network.config.ApiConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET(ApiConfig.URL_TOP_HEADLINES)
    suspend fun getArticles(
        @Query("sources") source: String
    ): Response<ArticleResponse>

}