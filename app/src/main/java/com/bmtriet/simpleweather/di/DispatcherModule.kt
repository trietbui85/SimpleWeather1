package com.bmtriet.simpleweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Used to provide coroutines dispatchers
 */
@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {

    @Provides
    @DefaultDispatcher
    internal fun provideDefaultCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    internal fun provideIoCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    internal fun provideMainCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
