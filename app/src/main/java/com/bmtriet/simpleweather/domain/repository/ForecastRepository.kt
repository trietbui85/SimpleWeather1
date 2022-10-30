package com.bmtriet.simpleweather.domain.repository

import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.utils.DataResult
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    suspend fun fetchForecastByCityName(
        cityName: String,
        forceReload: Boolean,
    ): Flow<DataResult<CityForecastModel>>
}
