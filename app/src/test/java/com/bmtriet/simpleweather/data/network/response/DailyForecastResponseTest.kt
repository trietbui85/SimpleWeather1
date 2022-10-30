package com.bmtriet.simpleweather.data.network.response

import org.junit.Assert.assertEquals
import org.junit.Test

class DailyForecastResponseTest {

    @Test
    fun testConstructor() {
        assertEquals(
            DailyForecastResponse(),
            DailyForecastResponse(
                dateTime = null,
                pressure = null,
                humidity = null,
                temperature = null,
                descriptions = null,
            ),
        )
    }
}
