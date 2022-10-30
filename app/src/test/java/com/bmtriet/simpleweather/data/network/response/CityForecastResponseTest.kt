package com.bmtriet.simpleweather.data.network.response

import org.junit.Assert.assertEquals
import org.junit.Test

class CityForecastResponseTest {

    @Test
    fun testConstructor() {
        assertEquals(
            CityForecastResponse(),
            CityForecastResponse(
                city = null,
                dailyForecast = null,
            ),
        )
    }
}
