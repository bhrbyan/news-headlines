package com.news.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class JsonContentTypeInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(ACCEPT, APPLICATION_JSON)
                .build()
        )
    }

    companion object {
        private const val ACCEPT: String = "accept"
        private const val APPLICATION_JSON = "application/json"
    }

}