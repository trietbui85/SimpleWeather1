package com.bmtriet.simpleweather.data.repository

import androidx.annotation.VisibleForTesting
import com.bmtriet.simpleweather.data.local.datasource.ForecastLocalDataSource
import com.bmtriet.simpleweather.data.network.datasource.ForecastRemoteDataSource
import com.bmtriet.simpleweather.di.IoDispatcher
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.repository.ForecastRepository
import com.bmtriet.simpleweather.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val remoteDataSource: ForecastRemoteDataSource,
    private val localDataSource: ForecastLocalDataSource,
    private val cacheExpireHelper: CacheExpireHelper,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ForecastRepository {

    override suspend fun fetchForecastByCityName(
        cityName: String,
        forceReload: Boolean,
    ): Flow<DataResult<CityForecastModel>> = flow {
        if (forceReload) {
            val result = fetchOnlineFirstThenSaveToCache(cityName)
            Timber.d("fetchOnlineFirstThenSaveToCache: Done: $result")
            emit(result)
        } else {
            // Load from cache, then network
            val cachedModel = fetchOfflineOnly(cityName)

            emit(DataResult.Success(cachedModel))

            if (cacheExpireHelper.isCacheExpired(cachedModel)) {
                Timber.d("Cache is more than 10 seconds. Fetch network again.")
                val result = fetchOnlineFirstThenSaveToCache(cityName)
                Timber.d("fetchOfflineFirstThenOnlineAndSaveToCache: Done: $result")
                emit(result)
            } else {
                Timber.d("Cache is still valid (less than 10 seconds).")
                Timber.d("cachedModel = $cachedModel")
            }
        }
    }

    @VisibleForTesting
    suspend fun fetchOnlineFirstThenSaveToCache(
        cityName: String,
    ): DataResult<CityForecastModel> = withContext(defaultDispatcher) {
        when (val result = remoteDataSource.getForecastByCityName(cityName)) {
            is DataResult.Success -> {
                // Save network data to cache, and emit
                val model = localDataSource.saveCityForecast(result.data!!)
                Timber.d("saveCityForecast Done: $model")
                return@withContext DataResult.Success(model)
            }
            else -> {
                return@withContext result
            }
        }
    }

    @VisibleForTesting
    suspend fun fetchOfflineOnly(cityName: String): CityForecastModel {
        return localDataSource.getCachedForecastByCity(cityName) ?: CityForecastModel()
    }
}
