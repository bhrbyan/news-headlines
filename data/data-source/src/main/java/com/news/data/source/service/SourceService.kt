package com.news.data.source.service

import com.news.data.source.model.SourceResponse
import com.news.network.config.ApiConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceService {

    @GET(ApiConfig.URL_SOURCES)
    suspend fun getSources(
        @Query("category") category: String
    ): Response<SourceResponse>

}