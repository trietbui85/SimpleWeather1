package com.bmtriet.simpleweather.data.repository

import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.model.CityModel
import com.bmtriet.simpleweather.utils.DateTimeUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CacheExpireHelperTest {

    @Test
    fun testIsCacheExpired() {
        val dateTimeUtils: DateTimeUtils = mockk {
            every { getCurrentTimeMs() } returns 1_300L
        }

        val cacheExpireHelper = CacheExpireHelper(
            dateTimeUtils,
            expireTimeInMs = 250L,
        )

        assertTrue(
            cacheExpireHelper.isCacheExpired(
                CityForecastModel(city = CityModel(lastTimeToSearch = 1_000L)),
            ),
        )

        assertFalse(
            cacheExpireHelper.isCacheExpired(
                CityForecastModel(city = CityModel(lastTimeToSearch = 1_200L)),
            ),
        )
    }
}
