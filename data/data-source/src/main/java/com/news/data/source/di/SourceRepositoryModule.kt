package com.news.data.source.di

import com.news.data.source.repository.SourceRepository
import com.news.data.source.repository.SourceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceRepositoryModule {

    @Binds
    abstract fun bindSourceRepository(sourceRepositoryImpl: SourceRepositoryImpl): SourceRepository

}