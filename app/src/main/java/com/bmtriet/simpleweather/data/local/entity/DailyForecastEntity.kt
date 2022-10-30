package com.bmtriet.simpleweather.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import com.bmtriet.simpleweather.data.local.dao.CityForecastDao

@Keep
@Entity(
    tableName = CityForecastDao.FORECAST_TABLE_NAME,
    primaryKeys = ["cityUniqueNameId", "dateYyyyMmDd"],
)
data class DailyForecastEntity(
    val cityUniqueNameId: Int = 0,
    val dateYyyyMmDd: Int = 0,
    val dateTimeFull: Long = 0,
    val averageTemp: Int = 0,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val description: String = "",
)
