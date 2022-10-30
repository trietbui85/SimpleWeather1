package com.bmtriet.simpleweather.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CityModelTest {

    @Test
    fun testConstructor() {
        assertEquals(
            CityModel(),
            CityModel(
                uniqueNameId = 0,
                cityDisplayName = "",
                lastTimeToSearch = 0,
            ),
        )
    }
}
