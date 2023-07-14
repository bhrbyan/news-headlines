package com.news.data.article.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName(value = "status") val status: String,
    @SerializedName(value = "totalResults") val total: Int,
    @SerializedName(value = "articles") val articles: List<ArticleData>,
)
