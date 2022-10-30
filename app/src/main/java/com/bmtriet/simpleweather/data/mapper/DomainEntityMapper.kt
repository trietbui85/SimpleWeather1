package com.bmtriet.simpleweather.data.mapper

import androidx.annotation.VisibleForTesting
import com.bmtriet.simpleweather.data.local.entity.CityEntity
import com.bmtriet.simpleweather.data.local.entity.CityForecastEntity
import com.bmtriet.simpleweather.data.local.entity.DailyForecastEntity
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.model.CityModel
import com.bmtriet.simpleweather.domain.model.DailyForecastModel
import com.bmtriet.simpleweather.utils.DateTimeUtils
import javax.inject.Inject

class DomainEntityMapper @Inject constructor(
    private val dateTimeUtils: DateTimeUtils,
) {
    fun toEntity(from: CityForecastModel) = CityForecastEntity(
        city = toEntityCity(from.city),
        dailyForecasts = from.dailyForecasts.map {
            toEntityWeather(it, from.city.uniqueNameId)
        },
    )

    @VisibleForTesting
    fun toEntityWeather(
        from: DailyForecastModel,
        uniqueNameId: Int,
    ) = DailyForecastEntity(
        cityUniqueNameId = uniqueNameId,
        dateYyyyMmDd = dateTimeUtils.toYyyyMmDd(from.dateTime),
        dateTimeFull = from.dateTime,
        averageTemp = from.averageTemp,
        pressure = from.pressure,
        humidity = from.humidity,
        description = from.description,
    )

    @VisibleForTesting
    fun toEntityCity(from: CityModel) = CityEntity(
        uniqueNameId = from.uniqueNameId,
        cityDisplayName = from.cityDisplayName,
        timeToSearch = from.lastTimeToSearch,
    )
}
