package com.bmtriet.simpleweather.data.network.datasource

import com.bmtriet.simpleweather.data.mapper.ResponseDomainMapper
import com.bmtriet.simpleweather.data.network.error.ErrorParser
import com.bmtriet.simpleweather.data.network.service.WeatherApi
import com.bmtriet.simpleweather.di.IoDispatcher
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ForecastRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val responseDomainMapper: ResponseDomainMapper,
    private val errorParser: ErrorParser,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ForecastRemoteDataSource {

    override suspend fun getForecastByCityName(
        cityName: String,
    ): DataResult<CityForecastModel> = withContext(defaultDispatcher) {
        val result = try {
            val response = weatherApi.getDayForecastByCity(city = cityName)
            Timber.d("getResult: code:${response.code()} data:${response.body()}")
            if (response.isSuccessful) {
                val model = responseDomainMapper.toDomain(response.body()!!, cityName)
                DataResult.Success(model)
            } else {
                val errType = errorParser.parseError(response.code())
                DataResult.Error(errType)
            }
        } catch (e: Exception) {
            val errType = errorParser.parseError(e)
            DataResult.Error(errType)
        }
        Timber.d("getForecastByCityName: result=$result")
        return@withContext result
    }
}
