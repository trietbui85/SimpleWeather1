package com.bmtriet.simpleweather.data.local.datasource

import com.bmtriet.simpleweather.domain.model.CityForecastModel

interface ForecastLocalDataSource {

    suspend fun getCachedForecastByCity(cityName: String): CityForecastModel?

    suspend fun saveCityForecast(model: CityForecastModel): CityForecastModel
}
