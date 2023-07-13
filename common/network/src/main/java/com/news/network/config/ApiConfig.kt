package com.news.network.config

object ApiConfig {

    const val BASE_URL: String = "https://newsapi.org/v2/"
    const val URL_EVERYTHING: String = "everything"
    const val URL_TOP_HEADLINES: String = "top-headlines"
    const val URL_SOURCES: String = "${URL_TOP_HEADLINES}sources"

}