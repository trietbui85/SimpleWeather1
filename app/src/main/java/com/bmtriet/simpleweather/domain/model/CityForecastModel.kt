package com.bmtriet.simpleweather.domain.model

import androidx.room.Entity

@Entity
data class CityForecastModel(
    val city: CityModel = CityModel(),
    val dailyForecasts: List<DailyForecastModel> = emptyList(),
)
