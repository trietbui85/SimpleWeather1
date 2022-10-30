package com.bmtriet.simpleweather.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DailyForecastResponse(
    @SerializedName("dt")
    val dateTime: Long? = null,

    @SerializedName("pressure")
    val pressure: Int? = null,

    @SerializedName("humidity")
    val humidity: Int? = null,

    @SerializedName("temp")
    val temperature: TemperatureResponse? = null,

    @SerializedName("weather")
    val descriptions: List<DescriptionResponse>? = null,
)
