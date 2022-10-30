package com.bmtriet.simpleweather.data.network.response

import com.google.gson.annotations.SerializedName

data class DescriptionResponse(
    @SerializedName("description")
    val description: String? = null,
)
