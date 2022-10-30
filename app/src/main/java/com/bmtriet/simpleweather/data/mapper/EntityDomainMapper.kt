package com.bmtriet.simpleweather.data.mapper

import com.bmtriet.simpleweather.data.local.entity.CityEntity
import com.bmtriet.simpleweather.data.local.entity.CityForecastEntity
import com.bmtriet.simpleweather.data.local.entity.DailyForecastEntity
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.model.CityModel
import com.bmtriet.simpleweather.domain.model.DailyForecastModel
import javax.inject.Inject

class EntityDomainMapper @Inject constructor() {

    fun toDomain(from: CityForecastEntity, uniqueNameId: Int) = CityForecastModel(
        city = toDomainCity(from.city, uniqueNameId),
        dailyForecasts = from.dailyForecasts.map { toDomainWeather(it) },
    )

    private fun toDomainWeather(from: DailyForecastEntity) = DailyForecastModel(
        dateTime = from.dateTimeFull,
        averageTemp = from.averageTemp,
        pressure = from.pressure,
        humidity = from.humidity,
        description = from.description,
    )

    private fun toDomainCity(from: CityEntity, uniqueNameId: Int) = CityModel(
        uniqueNameId = uniqueNameId,
        cityDisplayName = from.cityDisplayName,
        lastTimeToSearch = from.timeToSearch,
    )
}
