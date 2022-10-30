package com.bmtriet.simpleweather.data.mapper

import com.bmtriet.simpleweather.data.network.response.CityForecastResponse
import com.bmtriet.simpleweather.data.network.response.CityResponse
import com.bmtriet.simpleweather.data.network.response.DailyForecastResponse
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.model.CityModel
import com.bmtriet.simpleweather.domain.model.DailyForecastModel
import com.bmtriet.simpleweather.utils.DateTimeUtils
import com.bmtriet.simpleweather.utils.generateUniqueId
import javax.inject.Inject
import kotlin.math.roundToInt

class ResponseDomainMapper @Inject constructor(
    private val dateTimeUtils: DateTimeUtils,
) {

    fun toDomain(from: CityForecastResponse, cityName: String) = CityForecastModel(
        city = toDomainCity(from.city, cityName),
        dailyForecasts = from.dailyForecast?.map { toDomainForecast(it) }.orEmpty(),
    )

    private fun toDomainForecast(from: DailyForecastResponse) = DailyForecastModel(
        dateTime = from.dateTime ?: 0,
        averageTemp = from.temperature?.let {
            (it.minTemp ?: 0.0).plus(it.maxTemp ?: 0.0) / 2
        }?.roundToInt() ?: 0,
        pressure = from.pressure ?: 0,
        humidity = from.humidity ?: 0,
        description = from.descriptions?.firstOrNull()?.description.orEmpty(),
    )

    private fun toDomainCity(from: CityResponse?, cityName: String): CityModel {
        from ?: return CityModel()

        return CityModel(
            uniqueNameId = cityName.generateUniqueId(),
            cityDisplayName = from.name.orEmpty(),
            lastTimeToSearch = dateTimeUtils.getCurrentTimeMs(),
        )
    }
}
