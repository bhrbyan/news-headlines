package com.news.data.source.repository

import com.news.data.source.model.Source
import com.news.data.source.service.SourceService
import com.news.network.dispatcher.AppDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val sourceService: SourceService,
    private val appDispatcher: AppDispatcher
) :
    SourceRepository {

    override suspend fun getSources(category: String): Flow<List<Source>?> {
        return flow {
            val response = sourceService.getSources(category)
            if (response.isSuccessful) {

                val sources = response.body()?.sources?.map {
                    Source(
                        it.id,
                        it.name,
                        it.description,
                        it.url,
                        it.category,
                        it.language,
                        it.country
                    )
                }
                emit(sources)
            } else {
                emit(null)
            }
        }.flowOn(appDispatcher.io)
    }

}