package com.bmtriet.simpleweather.presentation.model

import com.bmtriet.simpleweather.R
import com.bmtriet.simpleweather.domain.model.DailyForecastModel
import com.bmtriet.simpleweather.utils.AndroidArgStringHolder
import com.bmtriet.simpleweather.utils.DateTimeUtils
import javax.inject.Inject

class DailyForestUiModelMapper @Inject constructor(
    private val dateTimeUtils: DateTimeUtils,
) {
    fun toUiModel(from: DailyForecastModel) = DailyForestUiModel(
        dateText = AndroidArgStringHolder(
            R.string.weather_date,
            arrayOf(dateTimeUtils.toSimpleDateText(from.dateTime)),
        ),
        averageTempText = AndroidArgStringHolder(
            R.string.weather_average_temp,
            arrayOf(from.averageTemp),
        ),
        pressureText = AndroidArgStringHolder(
            R.string.weather_pressure,
            arrayOf(from.pressure),
        ),
        humidityText = AndroidArgStringHolder(
            R.string.weather_humidity,
            arrayOf(from.humidity),
        ),
        descriptionText = AndroidArgStringHolder(
            R.string.weather_desc,
            arrayOf(from.description),
        ),
    )
}
