package com.bmtriet.simpleweather.presentation.model

import com.bmtriet.simpleweather.R
import com.bmtriet.simpleweather.domain.model.DailyForecastModel
import com.bmtriet.simpleweather.utils.AndroidArgStringHolder
import com.bmtriet.simpleweather.utils.DateTimeUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class DailyForestUiModelMapperTest {

    @Test
    fun testToUiModel() {
        val dateTimeUtils: DateTimeUtils = mockk {
            every { toSimpleDateText(1) } returns "1/1/1970"
        }
        val mapper = DailyForestUiModelMapper(dateTimeUtils)
        val from = DailyForecastModel(
            dateTime = 1,
            averageTemp = 10,
            pressure = 20,
            humidity = 30,
            description = "desc",
        )
        assertEquals(
            mapper.toUiModel(from),
            DailyForestUiModel(
                dateText = AndroidArgStringHolder(
                    R.string.weather_date,
                    arrayOf("1/1/1970"),
                ),
                averageTempText = AndroidArgStringHolder(
                    R.string.weather_average_temp,
                    arrayOf(10),
                ),
                pressureText = AndroidArgStringHolder(
                    R.string.weather_pressure,
                    arrayOf(20),
                ),
                humidityText = AndroidArgStringHolder(
                    R.string.weather_humidity,
                    arrayOf(30),
                ),
                descriptionText = AndroidArgStringHolder(
                    R.string.weather_desc,
                    arrayOf("desc"),
                ),
            ),
        )
    }
}
