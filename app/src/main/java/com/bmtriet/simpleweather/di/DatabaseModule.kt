package com.bmtriet.simpleweather.di

import android.content.Context
import androidx.room.Room
import com.bmtriet.simpleweather.BuildConfig
import com.bmtriet.simpleweather.data.local.WeatherDatabase
import com.bmtriet.simpleweather.data.repository.CacheExpireHelper
import com.bmtriet.simpleweather.utils.DateTimeUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideForecastDao(database: WeatherDatabase) = database.cityForecastDao()

    @Provides
    @Singleton
    fun provideCacheExpireHelper(
        dateTimeUtils: DateTimeUtils,
        @Named("CacheExpireTime") expireTimeInMs: Long,
    ) = CacheExpireHelper(dateTimeUtils, expireTimeInMs)

    @Provides
    @Singleton
    @Named("CacheExpireTime")
    @Suppress("MagicNumber")
    fun provideCacheExpireTime(): Long = if (BuildConfig.DEBUG) {
        // In Debug build, we set Cache Expire to 10 seconds
        10_000
    } else {
        // But in Release build, Cache Expire to 60 seconds
        60_000
    }
}
