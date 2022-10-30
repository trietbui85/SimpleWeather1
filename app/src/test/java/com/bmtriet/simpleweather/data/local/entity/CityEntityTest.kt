package com.bmtriet.simpleweather.data.local.entity

import org.junit.Assert.assertEquals
import org.junit.Test

class CityEntityTest {

    @Test
    fun testConstructor() {
        assertEquals(
            CityEntity(),
            CityEntity(
                uniqueNameId = 0,
                cityDisplayName = "",
                timeToSearch = 0,
            ),
        )
    }
}
