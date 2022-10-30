package com.bmtriet.simpleweather.domain.usecase

import com.bmtriet.simpleweather.di.IoDispatcher
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.repository.ForecastRepository
import com.bmtriet.simpleweather.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(
    private val repository: ForecastRepository,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : UseCase<ForecastParams, Flow<DataResult<CityForecastModel>>>(defaultDispatcher) {

    override suspend fun execute(
        param: ForecastParams,
    ): Flow<DataResult<CityForecastModel>> = when (param) {
        is CityForecastParam -> repository.fetchForecastByCityName(
            cityName = param.cityName,
            forceReload = param.forceReload,
        )
        // is LatLngForecastParam -> repository.fetchForecastByLatLng()
    }
}
