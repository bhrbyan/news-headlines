package com.news.network.config

import com.news.network.interceptor.HeaderInterceptor
import com.news.network.interceptor.JsonContentTypeInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClientConfig {

    private const val DEFAULT_TIMEOUT: Long = 30

    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(JsonContentTypeInterceptor())
            .build()
    }

}