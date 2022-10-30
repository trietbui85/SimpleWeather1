package com.bmtriet.simpleweather.di

import com.bmtriet.simpleweather.presentation.model.DailyForestUiModelMapper
import com.bmtriet.simpleweather.utils.DateTimeUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDailyForestUiModelMapper(
        dateTimeUtils: DateTimeUtils,
    ) = DailyForestUiModelMapper(dateTimeUtils)

    @Provides
    @Singleton
    fun provideDateTimeUtils() = DateTimeUtils
}
