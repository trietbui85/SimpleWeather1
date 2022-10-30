package com.bmtriet.simpleweather.di

import com.bmtriet.simpleweather.data.repository.ForecastRepositoryImpl
import com.bmtriet.simpleweather.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repository: ForecastRepositoryImpl): ForecastRepository
}
