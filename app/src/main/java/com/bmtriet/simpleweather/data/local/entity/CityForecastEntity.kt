package com.bmtriet.simpleweather.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.bmtriet.simpleweather.data.local.dao.CityForecastDao

@Entity(tableName = CityForecastDao.CITY_FORECAST_TABLE_NAME)
data class CityForecastEntity(
    @Embedded
    val city: CityEntity,

    @Relation(parentColumn = "uniqueNameId", entityColumn = "cityUniqueNameId")
    val dailyForecasts: List<DailyForecastEntity> = emptyList(),
)
