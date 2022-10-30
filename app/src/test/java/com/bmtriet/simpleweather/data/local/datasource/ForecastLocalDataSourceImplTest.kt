package com.bmtriet.simpleweather.data.local.datasource

import com.bmtriet.simpleweather.data.local.dao.CityForecastDao
import com.bmtriet.simpleweather.data.local.entity.CityForecastEntity
import com.bmtriet.simpleweather.data.mapper.DomainEntityMapper
import com.bmtriet.simpleweather.data.mapper.EntityDomainMapper
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ForecastLocalDataSourceImplTest {

    private val weatherDao: CityForecastDao = mockk()
    private val entityDomainMapper: EntityDomainMapper = mockk()
    private val domainEntityMapper: DomainEntityMapper = mockk()
    private val dispatcher = UnconfinedTestDispatcher()

    private val dataSource: ForecastLocalDataSource = ForecastLocalDataSourceImpl(
        weatherDao,
        entityDomainMapper,
        domainEntityMapper,
        dispatcher,
    )

    @Test
    fun testGetCachedForecastByCity() = runTest {
        coEvery {
            weatherDao.getCityWeather7DaysForecast(cityUniqueId)
        } returns cityForecastEntity
        every {
            entityDomainMapper.toDomain(cityForecastEntity, cityUniqueId)
        } returns cityForecastModel

        assertEquals(
            dataSource.getCachedForecastByCity(cityName),
            cityForecastModel,
        )
    }

    @Test
    fun testSaveCityForecast() = runTest {
        coEvery {
            weatherDao.upsertCityForecast(cityForecastEntity)
        } returns Unit
        every {
            domainEntityMapper.toEntity(cityForecastModel)
        } returns cityForecastEntity

        assertEquals(
            dataSource.saveCityForecast(cityForecastModel),
            cityForecastModel,
        )
    }

    companion object {
        private const val cityName = "Saigon"
        private val cityUniqueId = "SAIGON".hashCode()
        private val cityForecastEntity: CityForecastEntity = mockk()
        private val cityForecastModel: CityForecastModel = mockk()
    }
}
