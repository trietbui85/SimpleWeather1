package com.bmtriet.simpleweather.data.local.datasource

import com.bmtriet.simpleweather.data.local.dao.CityForecastDao
import com.bmtriet.simpleweather.data.mapper.DomainEntityMapper
import com.bmtriet.simpleweather.data.mapper.EntityDomainMapper
import com.bmtriet.simpleweather.di.IoDispatcher
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.utils.generateUniqueId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ForecastLocalDataSourceImpl @Inject constructor(
    private val weatherDao: CityForecastDao,
    private val entityDomainMapper: EntityDomainMapper,
    private val domainEntityMapper: DomainEntityMapper,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ForecastLocalDataSource {

    override suspend fun getCachedForecastByCity(
        cityName: String,
    ): CityForecastModel? = withContext(defaultDispatcher) {
        val cityUniqueId = cityName.generateUniqueId()
        val cityForecastEntity = weatherDao.getCityWeather7DaysForecast(cityUniqueId)
        Timber.d("getCachedForecastByCity: $cityForecastEntity")
        val model = if (cityForecastEntity == null) {
            null
        } else {
            entityDomainMapper.toDomain(cityForecastEntity, cityUniqueId)
        }
        return@withContext model
    }

    override suspend fun saveCityForecast(
        model: CityForecastModel,
    ): CityForecastModel = withContext(defaultDispatcher) {
        Timber.d("saveCityForecast: model=$model")
        val cityWeatherEntity = domainEntityMapper.toEntity(model)
        weatherDao.upsertCityForecast(cityWeatherEntity)
        return@withContext model
    }
}
