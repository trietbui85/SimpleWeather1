package com.bmtriet.simpleweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.bmtriet.simpleweather.data.local.entity.CityEntity
import com.bmtriet.simpleweather.data.local.entity.CityForecastEntity
import com.bmtriet.simpleweather.data.local.entity.DailyForecastEntity

@Dao
interface CityForecastDao {

    @Transaction
    suspend fun upsertCityForecast(cityForecastEntity: CityForecastEntity) {
        insertCity(cityForecastEntity.city)
        insertForecast(cityForecastEntity.dailyForecasts)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(data: CityEntity)

    @Query("Select * FROM $CITY_TABLE_NAME")
    suspend fun getAllCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(data: List<DailyForecastEntity>)

    @Query("Select * FROM $FORECAST_TABLE_NAME")
    suspend fun getAllForecasts(): List<DailyForecastEntity>

    @Transaction
    @Query(GET_7_DAY_FORECAST)
    suspend fun getCityWeather7DaysForecast(
        cityUniqueId: Int,
    ): CityForecastEntity?

    companion object {
        const val CITY_TABLE_NAME = "city"
        const val FORECAST_TABLE_NAME = "forecast"
        const val CITY_FORECAST_TABLE_NAME = "city_forecast"
        private const val GET_7_DAY_FORECAST = "SELECT * FROM $CITY_TABLE_NAME " +
            "WHERE $CITY_TABLE_NAME.uniqueNameId = :cityUniqueId"
    }
}
