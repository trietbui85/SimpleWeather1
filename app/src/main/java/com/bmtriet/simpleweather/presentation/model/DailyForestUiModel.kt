package com.bmtriet.simpleweather.presentation.model

import com.bmtriet.simpleweather.utils.AndroidArgStringHolder

data class DailyForestUiModel(
    val dateText: AndroidArgStringHolder = AndroidArgStringHolder(),
    val averageTempText: AndroidArgStringHolder = AndroidArgStringHolder(),
    val pressureText: AndroidArgStringHolder = AndroidArgStringHolder(),
    val humidityText: AndroidArgStringHolder = AndroidArgStringHolder(),
    val descriptionText: AndroidArgStringHolder = AndroidArgStringHolder(),
)
