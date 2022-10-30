package com.bmtriet.simpleweather.data.network.service

import com.bmtriet.simpleweather.data.network.response.CityForecastResponse
import com.bmtriet.simpleweather.data.network.response.CityResponse
import com.bmtriet.simpleweather.data.network.response.DailyForecastResponse
import com.bmtriet.simpleweather.data.network.response.DescriptionResponse
import com.bmtriet.simpleweather.data.network.response.TemperatureResponse
import com.bmtriet.simpleweather.data.network.service.WeatherApi.Companion.DEFAULT_UNIT
import com.bmtriet.simpleweather.data.network.service.WeatherApi.Companion.NUM_OF_FORECAST_DAYS
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherApiTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val weatherApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(WeatherApi::class.java)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testDefaultConstant() {
        assertEquals(DEFAULT_UNIT, "metric")
        assertEquals(NUM_OF_FORECAST_DAYS, 7)
    }

    @Test
    fun testParamOfGetDayForecastByCity_Success() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(sevenDaysForecastResponse200))
        val response = weatherApi.getDayForecastByCity(city = "saigon")

        assertNotNull(response)
        assertTrue(response.isSuccessful)
        assertEquals(
            response.body()!!,
            CityForecastResponse(
                city = CityResponse(
                    id = 1580578,
                    name = "Ho Chi Minh City",
                ),
                dailyForecast = listOf(
                    DailyForecastResponse(
                        dateTime = 1666584000,
                        pressure = 1011,
                        humidity = 68,
                        temperature = TemperatureResponse(minTemp = 297.53, maxTemp = 305.36),
                        descriptions = listOf(
                            DescriptionResponse("moderate rain"),
                        ),
                    ),
                ),
            ),
        )

        val request = response.raw().request
        assertEquals(request.method, "GET")
        request.url.let {
            assertEquals(it.pathSegments, listOf("forecast", "daily"))
            assertEquals(it.queryParameter("q"), "saigon")
            assertEquals(it.queryParameter("units"), "metric")
            assertEquals(it.queryParameter("cnt"), "7")
        }
    }

    @Test
    fun testParamOfGetDayForecastByCity_Error401() = runTest {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(401).setBody(sevenDaysForecastResponse401),
        )
        val response = weatherApi.getDayForecastByCity(city = "saigon")

        assertNotNull(response)
        assertFalse(response.isSuccessful)
        assertEquals(response.code(), 401)
    }
}
