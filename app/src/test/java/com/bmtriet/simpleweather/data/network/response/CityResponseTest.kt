package com.bmtriet.simpleweather.data.network.response

import org.junit.Assert.assertEquals
import org.junit.Test

class CityResponseTest {

    @Test
    fun testConstructor() {
        assertEquals(
            CityResponse(),
            CityResponse(
                id = null,
                name = null,
            ),
        )
    }
}
