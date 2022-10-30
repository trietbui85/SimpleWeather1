package com.bmtriet.simpleweather.data.network.response

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(
    @SerializedName("min")
    val minTemp: Double? = null,
    @SerializedName("max")
    val maxTemp: Double? = null,
)
