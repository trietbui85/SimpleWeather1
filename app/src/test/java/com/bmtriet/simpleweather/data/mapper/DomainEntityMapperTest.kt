package com.bmtriet.simpleweather.data.mapper

import com.bmtriet.simpleweather.utils.DateTimeUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class DomainEntityMapperTest : MapperDataTest() {

    private val dateTimeUtils: DateTimeUtils = mockk {
        every { getCurrentTimeMs() } returns timeToSearchMs
        every { toYyyyMmDd(searchResultTimeMs) } returns searchResultAsYyyyMmDd
    }
    private val mapper = DomainEntityMapper(dateTimeUtils)

    @Test
    fun toEntity() {
        assertEquals(mapper.toEntity(cityForecastModel), cityForecastEntity)
    }
}
