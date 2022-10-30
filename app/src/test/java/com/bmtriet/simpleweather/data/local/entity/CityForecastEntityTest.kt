package com.bmtriet.simpleweather.data.local.entity

import org.junit.Assert.assertEquals
import org.junit.Test

class CityForecastEntityTest {

    @Test
    fun testConstructor() {
        assertEquals(
            CityForecastEntity(CityEntity()),
            CityForecastEntity(
                city = CityEntity(),
                dailyForecasts = emptyList(),
            ),
        )
    }
}
