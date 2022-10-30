package com.bmtriet.simpleweather.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CityForecastResponse(
    @SerializedName("city")
    val city: CityResponse? = null,

    @SerializedName("list")
    val dailyForecast: List<DailyForecastResponse>? = null,
)
