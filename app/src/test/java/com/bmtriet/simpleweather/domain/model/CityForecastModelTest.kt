package com.bmtriet.simpleweather.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CityForecastModelTest {

    @Test
    fun testConstructor() {
        assertEquals(
            CityForecastModel(),
            CityForecastModel(
                city = CityModel(),
                dailyForecasts = emptyList(),
            ),
        )
    }
}
