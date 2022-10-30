package com.bmtriet.simpleweather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bmtriet.simpleweather.data.local.dao.CityForecastDao
import com.bmtriet.simpleweather.data.local.entity.CityEntity
import com.bmtriet.simpleweather.data.local.entity.DailyForecastEntity

@Database(
    entities = [
        CityEntity::class,
        DailyForecastEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "city_weather.db"
    }

    abstract fun cityForecastDao(): CityForecastDao
}
