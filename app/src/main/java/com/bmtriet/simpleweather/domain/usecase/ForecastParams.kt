package com.bmtriet.simpleweather.domain.usecase

sealed class ForecastParams

data class CityForecastParam(
    val cityName: String = "",
    val forceReload: Boolean = false,
) : ForecastParams()

// Later, we can have LatLngForecastParam
