package com.news.data.source.repository

import com.news.data.source.model.Source
import kotlinx.coroutines.flow.Flow

interface SourceRepository {

    suspend fun getSources(category: String): Flow<List<Source>?>

}