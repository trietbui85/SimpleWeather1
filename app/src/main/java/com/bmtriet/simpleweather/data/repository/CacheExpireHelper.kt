package com.bmtriet.simpleweather.data.repository

import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.utils.DateTimeUtils
import javax.inject.Inject

class CacheExpireHelper @Inject constructor(
    private val dateTimeUtils: DateTimeUtils,
    private val expireTimeInMs: Long,
) {

    fun isCacheExpired(model: CityForecastModel): Boolean {
        val diffMs = dateTimeUtils.getCurrentTimeMs() - model.city.lastTimeToSearch

        return diffMs > expireTimeInMs
    }
}
