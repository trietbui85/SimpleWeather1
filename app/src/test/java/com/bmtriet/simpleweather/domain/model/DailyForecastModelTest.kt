package com.bmtriet.simpleweather.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class DailyForecastModelTest {

    @Test
    fun testConstructor() {
        assertEquals(
            DailyForecastModel(),
            DailyForecastModel(
                dateTime = 0,
                averageTemp = 0,
                pressure = 0,
                humidity = 0,
                description = "",
            ),
        )
    }
}
