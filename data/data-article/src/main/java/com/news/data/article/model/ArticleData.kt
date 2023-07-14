package com.news.data.article.model

import com.google.gson.annotations.SerializedName

data class ArticleData(
    @SerializedName(value = "author") val author: String,
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "description") val description: String,
    @SerializedName(value = "url") val url: String,
    @SerializedName(value = "urlToImage") val urlToImage: String,
    @SerializedName(value = "publishedAt") val publishedAt: String,
    @SerializedName(value = "content") val content: String
)
