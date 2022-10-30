package com.bmtriet.simpleweather.presentation.model

import com.bmtriet.simpleweather.utils.AndroidArgStringHolder
import org.junit.Assert.assertEquals
import org.junit.Test

class DailyForestUiModelTest {

    @Test
    fun testConstructor() {
        assertEquals(
            DailyForestUiModel(),
            DailyForestUiModel(
                dateText = AndroidArgStringHolder(),
                averageTempText = AndroidArgStringHolder(),
                pressureText = AndroidArgStringHolder(),
                humidityText = AndroidArgStringHolder(),
                descriptionText = AndroidArgStringHolder(),
            ),
        )
    }
}
