package com.bmtriet.simpleweather.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bmtriet.simpleweather.data.local.dao.CityForecastDao

@Keep
@Entity(
    tableName = CityForecastDao.CITY_TABLE_NAME,
)
data class CityEntity(
    @PrimaryKey
    val uniqueNameId: Int = 0,

    val cityDisplayName: String = "",

    val timeToSearch: Long = 0,
)
