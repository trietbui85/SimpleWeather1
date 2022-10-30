package com.bmtriet.simpleweather.data.mapper

import com.bmtriet.simpleweather.data.local.entity.CityEntity
import com.bmtriet.simpleweather.data.local.entity.CityForecastEntity
import com.bmtriet.simpleweather.data.local.entity.DailyForecastEntity
import com.bmtriet.simpleweather.data.network.response.CityForecastResponse
import com.bmtriet.simpleweather.data.network.response.CityResponse
import com.bmtriet.simpleweather.data.network.response.DailyForecastResponse
import com.bmtriet.simpleweather.data.network.response.DescriptionResponse
import com.bmtriet.simpleweather.data.network.response.TemperatureResponse
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.model.CityModel
import com.bmtriet.simpleweather.domain.model.DailyForecastModel

open class MapperDataTest {

    protected val timeToSearchMs = 1234567889L
    protected val searchResultTimeMs = 1234567890L // Friday, 13 February 2009 23:31:30
    protected val searchResultAsYyyyMmDd = 20090213
    protected val uniqueCityNameId: Int = -1856473397 // "saigon".hashCode()

    protected val cityResponse = CityResponse(
        id = 1580578,
        name = "Ho Chi Minh City",
    )

    protected val cityModel = CityModel(
        uniqueNameId = uniqueCityNameId,
        cityDisplayName = "Ho Chi Minh City",
        lastTimeToSearch = timeToSearchMs,
    )

    protected val cityEntity = CityEntity(
        uniqueNameId = uniqueCityNameId,
        cityDisplayName = "Ho Chi Minh City",
        timeToSearch = timeToSearchMs,
    )

    protected val dailyForecastResponse = DailyForecastResponse(
        dateTime = searchResultTimeMs,
        pressure = 20,
        humidity = 30,
        temperature = TemperatureResponse(minTemp = 5.0, maxTemp = 15.0),
        descriptions = listOf(
            DescriptionResponse("desc"),
        ),
    )
    protected val dailyForecastModel = DailyForecastModel(
        dateTime = searchResultTimeMs,
        averageTemp = 10,
        pressure = 20,
        humidity = 30,
        description = "desc",
    )

    private val dailyForecastEntity = DailyForecastEntity(
        cityUniqueNameId = uniqueCityNameId,
        dateYyyyMmDd = searchResultAsYyyyMmDd,
        dateTimeFull = searchResultTimeMs,
        averageTemp = 10,
        pressure = 20,
        humidity = 30,
        description = "desc",
    )

    protected val cityForecastResponse = CityForecastResponse(
        city = cityResponse,
        dailyForecast = listOf(dailyForecastResponse),
    )

    protected val cityForecastModel = CityForecastModel(
        city = cityModel,
        dailyForecasts = listOf(dailyForecastModel),
    )

    protected val cityForecastEntity = CityForecastEntity(
        city = cityEntity,
        dailyForecasts = listOf(dailyForecastEntity),
    )
}
