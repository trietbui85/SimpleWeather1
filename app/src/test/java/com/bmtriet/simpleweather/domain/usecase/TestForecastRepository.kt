package com.bmtriet.simpleweather.domain.usecase

import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.model.CityModel
import com.bmtriet.simpleweather.domain.model.DailyForecastModel
import com.bmtriet.simpleweather.domain.repository.ForecastRepository
import com.bmtriet.simpleweather.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TestForecastRepository : ForecastRepository {
    override suspend fun fetchForecastByCityName(
        cityName: String,
        forceReload: Boolean,
    ): Flow<DataResult<CityForecastModel>> = flowOf(DataResult.Success(cityForecastModel))

    companion object {
        val cityForecastModel = CityForecastModel(
            city = CityModel(uniqueNameId = 1),
            dailyForecasts = listOf(
                DailyForecastModel(dateTime = 1),
            ),
        )
    }
}
