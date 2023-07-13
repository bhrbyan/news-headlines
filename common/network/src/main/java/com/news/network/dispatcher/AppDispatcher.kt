package com.news.network.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatcher {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}