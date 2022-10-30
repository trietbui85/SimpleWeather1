package com.bmtriet.simpleweather.data.repository

import app.cash.turbine.test
import com.bmtriet.simpleweather.data.local.datasource.ForecastLocalDataSource
import com.bmtriet.simpleweather.data.network.datasource.ForecastRemoteDataSource
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.utils.DataResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ForecastRepositoryImplTest {

    private val remoteDataSource: ForecastRemoteDataSource = mockk()
    private val localDataSource: ForecastLocalDataSource = mockk()
    private val cacheExpireHelper: CacheExpireHelper = mockk()
    private val dispatcher = UnconfinedTestDispatcher()

    private val repository = spyk(
        ForecastRepositoryImpl(
            remoteDataSource,
            localDataSource,
            cacheExpireHelper,
            dispatcher,
        ),
    )

    @Test
    fun testFetchOnlineFirstThenSaveToCache() = runTest {
        coEvery {
            remoteDataSource.getForecastByCityName(cityName)
        } returns DataResult.Success(cityForecastModel)
        coEvery {
            localDataSource.saveCityForecast(cityForecastModel)
        } returns cityForecastModelFromDb

        assertEquals(
            repository.fetchOnlineFirstThenSaveToCache(cityName),
            DataResult.Success(cityForecastModelFromDb),
        )
    }

    @Test
    fun fetchForecastByCityName_ForeReload() = runTest {
        coEvery {
            repository.fetchOnlineFirstThenSaveToCache(cityName)
        } returns DataResult.Success(cityForecastModel)

        repository.fetchForecastByCityName(cityName, true).test {
            assertEquals(
                awaitItem(),
                DataResult.Success(cityForecastModel),
            )
            awaitComplete()
        }
    }

    @Test
    fun fetchForecastByCityName_CacheStillValid() = runTest {
        coEvery {
            repository.fetchOfflineOnly(cityName)
        } returns cityForecastModel
        every {
            cacheExpireHelper.isCacheExpired(cityForecastModel)
        } returns false

        repository.fetchForecastByCityName(cityName, false).test {
            assertEquals(
                awaitItem(),
                DataResult.Success(cityForecastModel),
            )
            awaitComplete()
        }
    }

    @Test
    fun fetchForecastByCityName_CacheStillExpired() = runTest {
        coEvery {
            repository.fetchOfflineOnly(cityName)
        } returns cityForecastModel
        coEvery {
            repository.fetchOnlineFirstThenSaveToCache(cityName)
        } returns DataResult.Success(cityForecastModelFromDb)
        every {
            cacheExpireHelper.isCacheExpired(cityForecastModel)
        } returns true

        repository.fetchForecastByCityName(cityName, false).test {
            assertEquals(
                awaitItem(),
                DataResult.Success(cityForecastModel),
            )
            assertEquals(
                awaitItem(),
                DataResult.Success(cityForecastModelFromDb),
            )
            awaitComplete()
        }
    }

    companion object {
        private const val cityName = "saigon"
        private val cityForecastModel: CityForecastModel = mockk()
        private val cityForecastModelFromDb: CityForecastModel = mockk()
    }
}
