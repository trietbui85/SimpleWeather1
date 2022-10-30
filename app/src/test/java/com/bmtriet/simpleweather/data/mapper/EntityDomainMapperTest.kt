package com.bmtriet.simpleweather.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test

class EntityDomainMapperTest : MapperDataTest() {

    private val mapper = EntityDomainMapper()

    @Test
    fun toDomain() {
        assertEquals(
            mapper.toDomain(cityForecastEntity, uniqueNameId = uniqueCityNameId),
            cityForecastModel,
        )
    }
}
