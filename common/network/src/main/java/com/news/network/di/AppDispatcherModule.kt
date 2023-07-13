package com.news.network.di

import com.news.network.dispatcher.AppDispatcher
import com.news.network.dispatcher.AppDispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDispatcherModule {

    @Binds
    @Singleton
    abstract fun bindAppDispatcher(appDispatcherImpl: AppDispatcherImpl): AppDispatcher

}