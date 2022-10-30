package com.bmtriet.simpleweather.domain.model

data class DailyForecastModel(
    val dateTime: Long = 0,
    val averageTemp: Int = 0,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val description: String = "",
)
