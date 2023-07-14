package com.news.data.article.di

import com.news.data.article.service.ArticleService
import com.news.network.config.ApiConfig
import com.news.network.config.OkHttpClientConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ArticleServiceModule {

    @Provides
    fun provideArticleService(): ArticleService {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .client(OkHttpClientConfig.getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticleService::class.java)
    }

}