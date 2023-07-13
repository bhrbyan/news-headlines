package com.news.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(AUTHORIZATION_HEADER, "$AUTHORIZATION_HEADER_TOKEN $APP_ID")
                .build()
        )
    }

    companion object {
        private const val APP_ID: String = "8c151fd173b9452eabaad3d6c2fbaa20"
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val AUTHORIZATION_HEADER_TOKEN = "Bearer"
    }

}