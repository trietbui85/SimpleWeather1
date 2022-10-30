package com.bmtriet.simpleweather.data.mapper

import com.bmtriet.simpleweather.utils.DateTimeUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ResponseDomainMapperTest : MapperDataTest() {

    private val dateTimeUtils: DateTimeUtils = mockk {
        every { getCurrentTimeMs() } returns timeToSearchMs
    }
    private val mapper = ResponseDomainMapper(dateTimeUtils)

    @Test
    fun toDomain() {
        assertEquals(mapper.toDomain(cityForecastResponse, "Saigon"), cityForecastModel)
    }
}
