package com.bmtriet.simpleweather.presentation.adapter

import android.app.Activity
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bmtriet.simpleweather.R
import com.bmtriet.simpleweather.databinding.DailyForecastItemBinding
import com.bmtriet.simpleweather.presentation.model.DailyForestUiModel
import com.bmtriet.simpleweather.testutils.assertHasHeightWrapContent
import com.bmtriet.simpleweather.testutils.assertHasMarginBottom
import com.bmtriet.simpleweather.testutils.assertHasPaddingBottom
import com.bmtriet.simpleweather.testutils.assertHasPaddingEnd
import com.bmtriet.simpleweather.testutils.assertHasPaddingStart
import com.bmtriet.simpleweather.testutils.assertHasPaddingTop
import com.bmtriet.simpleweather.testutils.assertHasTextContent
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DailyForecastViewHolderTest {

    private val activity = Robolectric.buildActivity(Activity::class.java).get()
    private val vh = spyk(
        DailyForecastViewHolder(activity.findViewById(android.R.id.content) as ViewGroup),
    )

    private val item = DailyForestUiModel()

    private val binding: DailyForecastItemBinding = vh.binding

    @Test
    fun verifyRootView_UiElement() = binding.root.let {
        it.assertHasPaddingStart(8)
        it.assertHasPaddingEnd(8)
        it.assertHasPaddingTop(4)
        it.assertHasPaddingBottom(4)
        assertEquals(it.orientation, LinearLayout.VERTICAL)
    }

    @Test
    fun verifyTextViewDate_UiElement() = binding.tvDate.let {
        it.assertHasHeightWrapContent()
        it.assertHasMarginBottom(4)
        it.assertHasTextContent(R.string.weather_date)
    }

    @Test
    fun verifyTextViewAverageTemp_UiElement() = binding.tvAverageTemp.let {
        it.assertHasHeightWrapContent()
        it.assertHasMarginBottom(4)
        it.assertHasTextContent(R.string.weather_average_temp)
    }

    @Test
    fun verifyTextViewPressure_UiElement() = binding.tvPressure.let {
        it.assertHasHeightWrapContent()
        it.assertHasMarginBottom(4)
        it.assertHasTextContent(R.string.weather_pressure)
    }

    @Test
    fun verifyTextViewHumidity_UiElement() = binding.tvHumidity.let {
        it.assertHasHeightWrapContent()
        it.assertHasMarginBottom(4)
        it.assertHasTextContent(R.string.weather_humidity)
    }

    @Test
    fun verifyTextViewDesc_UiElement() = binding.tvDesc.let {
        it.assertHasHeightWrapContent()
        it.assertHasTextContent(R.string.weather_desc)
    }

    @Test
    fun testBinding() {
        vh.bind(item)

        verify {
            vh.setViewHolderText(binding.tvDate, item.dateText)
            vh.setViewHolderText(binding.tvAverageTemp, item.averageTempText)
            vh.setViewHolderText(binding.tvPressure, item.pressureText)
            vh.setViewHolderText(binding.tvHumidity, item.humidityText)
            vh.setViewHolderText(binding.tvDesc, item.descriptionText)
        }
    }
}
