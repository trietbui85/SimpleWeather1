package com.bmtriet.simpleweather.di

import com.bmtriet.simpleweather.data.local.datasource.ForecastLocalDataSource
import com.bmtriet.simpleweather.data.local.datasource.ForecastLocalDataSourceImpl
import com.bmtriet.simpleweather.data.network.datasource.ForecastRemoteDataSource
import com.bmtriet.simpleweather.data.network.datasource.ForecastRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(
        remoteDataSource: ForecastRemoteDataSourceImpl,
    ): ForecastRemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(
        remoteDataSource: ForecastLocalDataSourceImpl,
    ): ForecastLocalDataSource
}
