package com.bmtriet.simpleweather.data.network.response

import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureResponseTest {

    @Test
    fun testConstructor() {
        assertEquals(
            TemperatureResponse(),
            TemperatureResponse(
                minTemp = null,
                maxTemp = null,
            ),
        )
    }
}
