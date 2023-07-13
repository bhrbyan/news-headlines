package com.news.data.source.model

import com.google.gson.annotations.SerializedName

data class SourceData(
    @SerializedName(value = "id") val id: String,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "description") val description: String,
    @SerializedName(value = "url") val url: String,
    @SerializedName(value = "category") val category: String,
    @SerializedName(value = "language") val language: String,
    @SerializedName(value = "country") val country: String,
)
