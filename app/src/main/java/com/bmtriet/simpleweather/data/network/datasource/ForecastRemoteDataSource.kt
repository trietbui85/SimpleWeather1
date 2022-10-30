package com.bmtriet.simpleweather.data.network.datasource

import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.utils.DataResult

interface ForecastRemoteDataSource {

    suspend fun getForecastByCityName(cityName: String): DataResult<CityForecastModel>
}
