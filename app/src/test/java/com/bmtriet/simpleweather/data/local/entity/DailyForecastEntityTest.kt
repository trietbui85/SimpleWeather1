package com.bmtriet.simpleweather.data.local.entity

import org.junit.Assert.assertEquals
import org.junit.Test

class DailyForecastEntityTest {

    @Test
    fun testConstructor() {
        assertEquals(
            DailyForecastEntity(),
            DailyForecastEntity(
                cityUniqueNameId = 0,
                dateYyyyMmDd = 0,
                dateTimeFull = 0,
                averageTemp = 0,
                pressure = 0,
                humidity = 0,
                description = "",
            ),
        )
    }
}
