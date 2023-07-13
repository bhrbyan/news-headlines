package com.news.data.source.usecase

import com.news.data.source.model.Source
import com.news.data.source.repository.SourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(private val sourceRepository: SourceRepository) {

    suspend operator fun invoke(category: String): Flow<List<Source>?> {
        return sourceRepository.getSources(category)
    }

}