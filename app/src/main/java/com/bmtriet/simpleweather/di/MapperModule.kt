package com.bmtriet.simpleweather.di

import com.bmtriet.simpleweather.data.mapper.DomainEntityMapper
import com.bmtriet.simpleweather.data.mapper.EntityDomainMapper
import com.bmtriet.simpleweather.data.mapper.ResponseDomainMapper
import com.bmtriet.simpleweather.utils.DateTimeUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MapperModule {

    @Provides
    internal fun provideDomainEntityMapper(
        dateTimeUtils: DateTimeUtils,
    ) = DomainEntityMapper(dateTimeUtils)

    @Provides
    internal fun provideEntityDomainMapper() = EntityDomainMapper()

    @Provides
    internal fun provideResponseDomainMapper(
        dateTimeUtils: DateTimeUtils,
    ) = ResponseDomainMapper(dateTimeUtils)
}
