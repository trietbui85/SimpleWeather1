package com.bmtriet.simpleweather.data.network.datasource

import com.bmtriet.simpleweather.data.mapper.ResponseDomainMapper
import com.bmtriet.simpleweather.data.network.error.ErrorParser
import com.bmtriet.simpleweather.data.network.error.ErrorType.INVALID_API_KEY
import com.bmtriet.simpleweather.data.network.response.CityForecastResponse
import com.bmtriet.simpleweather.data.network.service.WeatherApi
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.utils.DataResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class ForecastRemoteDataSourceImplTest {

    private val weatherApi: WeatherApi = mockk()
    private val responseDomainMapper: ResponseDomainMapper = mockk()
    private val errorParser: ErrorParser = mockk()
    private val dispatcher = UnconfinedTestDispatcher()

    private val dataSource = ForecastRemoteDataSourceImpl(
        weatherApi,
        responseDomainMapper,
        errorParser,
        dispatcher,
    )

    @Test
    fun testGetForecastByCityName_Success() = runTest {
        coEvery {
            weatherApi.getDayForecastByCity(city = cityName)
        } returns cityForecastResponse
        every {
            responseDomainMapper.toDomain(cityForecastResponse.body()!!, cityName)
        } returns cityForecastModel

        assertEquals(
            dataSource.getForecastByCityName(cityName),
            DataResult.Success(cityForecastModel),
        )
    }

    @Test
    fun testGetForecastByCityName_Failed() = runTest {
        coEvery {
            weatherApi.getDayForecastByCity(city = cityName)
        } returns Response.error(401, mockk(relaxed = true))
        every {
            errorParser.parseError(401)
        } returns INVALID_API_KEY

        assertEquals(
            dataSource.getForecastByCityName(cityName),
            DataResult.Error(INVALID_API_KEY),
        )
    }

    companion object {
        private const val cityName = "Saigon"
        private val cityForecastResponse = Response.success(200, mockk<CityForecastResponse>())
        private val cityForecastModel: CityForecastModel = mockk()
    }
}
