package com.bmtriet.simpleweather.presentation.adapter

import com.bmtriet.simpleweather.R
import com.bmtriet.simpleweather.presentation.model.DailyForestUiModel
import com.bmtriet.simpleweather.utils.AndroidArgStringHolder
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DailyForecastDiffCallbackTest {

    private val diffCallback = DailyForecastDiffCallback()

    private val oldItem = DailyForestUiModel(dateText = AndroidArgStringHolder())
    private val newItem = DailyForestUiModel(dateText = AndroidArgStringHolder())
    private val newItem2 = DailyForestUiModel(
        dateText = AndroidArgStringHolder(R.string.app_name),
    )

    @Test
    fun testAreItemsTheSame() {
        assertTrue(diffCallback.areItemsTheSame(oldItem, newItem))
        assertFalse(diffCallback.areItemsTheSame(oldItem, newItem2))
    }

    @Test
    fun testAreContentsTheSame() {
        assertTrue(diffCallback.areContentsTheSame(oldItem, newItem))
        assertTrue(diffCallback.areContentsTheSame(oldItem, newItem2))
    }
}
