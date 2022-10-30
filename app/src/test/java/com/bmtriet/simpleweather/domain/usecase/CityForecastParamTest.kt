package com.bmtriet.simpleweather.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test

class CityForecastParamTest {

    @Test
    fun testConstructor() {
        assertEquals(
            CityForecastParam(),
            CityForecastParam(
                cityName = "",
                forceReload = false,
            ),
        )
    }
}
