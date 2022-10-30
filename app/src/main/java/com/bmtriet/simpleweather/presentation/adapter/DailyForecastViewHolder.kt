package com.bmtriet.simpleweather.presentation.adapter

import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import com.bmtriet.simpleweather.R
import com.bmtriet.simpleweather.databinding.DailyForecastItemBinding
import com.bmtriet.simpleweather.presentation.model.DailyForestUiModel
import com.bmtriet.simpleweather.utils.BaseViewHolderWithLayout

class DailyForecastViewHolder(
    parent: ViewGroup,
) : BaseViewHolderWithLayout(parent, R.layout.daily_forecast_item) {

    @VisibleForTesting
    val binding = DailyForecastItemBinding.bind(itemView)

    fun bind(item: DailyForestUiModel) = binding.run {
        setViewHolderText(tvDate, item.dateText)
        setViewHolderText(tvAverageTemp, item.averageTempText)
        setViewHolderText(tvPressure, item.pressureText)
        setViewHolderText(tvHumidity, item.humidityText)
        setViewHolderText(tvDesc, item.descriptionText)

        binding.root.contentDescription = "${tvDate.text}, ${tvAverageTemp.text}, " +
            "${tvPressure.text}, ${tvHumidity.text}, ${tvDesc.text}"
    }
}
