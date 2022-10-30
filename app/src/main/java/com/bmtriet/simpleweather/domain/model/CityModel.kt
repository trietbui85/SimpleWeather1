package com.bmtriet.simpleweather.domain.model

data class CityModel(
    val uniqueNameId: Int = 0,
    val cityDisplayName: String = "",
    val lastTimeToSearch: Long = 0,
)
