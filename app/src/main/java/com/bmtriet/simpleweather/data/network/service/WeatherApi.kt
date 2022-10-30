package com.bmtriet.simpleweather.data.network.service

import androidx.annotation.VisibleForTesting
import com.bmtriet.simpleweather.data.network.response.CityForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast/daily")
    suspend fun getDayForecastByCity(
        @Query("q") city: String,
        @Query("units") units: String = DEFAULT_UNIT,
        @Query("cnt") numOfDay: Int = NUM_OF_FORECAST_DAYS,
    ): Response<CityForecastResponse>

    companion object {
        @VisibleForTesting
        const val DEFAULT_UNIT = "metric"

        @VisibleForTesting
        const val NUM_OF_FORECAST_DAYS = 7
    }
}
