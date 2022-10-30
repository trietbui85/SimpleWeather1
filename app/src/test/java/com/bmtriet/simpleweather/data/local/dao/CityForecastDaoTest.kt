package com.bmtriet.simpleweather.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bmtriet.simpleweather.data.local.WeatherDatabase
import com.bmtriet.simpleweather.data.local.entity.CityEntity
import com.bmtriet.simpleweather.data.local.entity.DailyForecastEntity
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CityForecastDaoTest {

    private lateinit var cityForecastDao: CityForecastDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext() as Context
        val database: WeatherDatabase = Room.inMemoryDatabaseBuilder(
            context,
            WeatherDatabase::class.java,
        ).build()
        cityForecastDao = database.cityForecastDao()
    }

    @Test
    fun testInsertCityThenGetAll() = runTest {
        cityForecastDao.insertCity(cityEntity)
        val cities = cityForecastDao.getAllCities()
        assertEquals(cities.size, 1)
        assertEquals(cityEntity, cities.firstOrNull())
    }

    @Test
    fun testInsertDailyForecastThenGetAll() = runTest {
        cityForecastDao.insertForecast(listOf(dailyForecastEntity))
        val forecasts = cityForecastDao.getAllForecasts()
        assertEquals(forecasts.size, 1)
        assertEquals(dailyForecastEntity, forecasts.firstOrNull())
    }

    companion object {
        private val cityEntity = CityEntity(
            uniqueNameId = 123,
            cityDisplayName = "Saigon",
            timeToSearch = 2,
        )
        private val dailyForecastEntity = DailyForecastEntity(
            cityUniqueNameId = 123,
            dateYyyyMmDd = 20090213, // Friday, 13 February 2009 23:31:30
            dateTimeFull = 1234567890,
            averageTemp = 10,
            pressure = 20,
            humidity = 30,
            description = "desc",
        )
    }
}
