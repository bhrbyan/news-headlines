package com.news.data.source.model

import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @SerializedName(value = "status") val status: String,
    @SerializedName(value = "sources") val sources: List<SourceData>,
)
