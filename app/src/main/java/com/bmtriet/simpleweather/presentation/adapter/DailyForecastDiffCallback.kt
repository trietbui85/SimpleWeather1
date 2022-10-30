package com.bmtriet.simpleweather.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bmtriet.simpleweather.presentation.model.DailyForestUiModel

class DailyForecastDiffCallback : DiffUtil.ItemCallback<DailyForestUiModel>() {
    override fun areItemsTheSame(
        oldItem: DailyForestUiModel,
        newItem: DailyForestUiModel,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DailyForestUiModel,
        newItem: DailyForestUiModel,
    ): Boolean {
        // already compare in `areItemsTheSame`
        return true
    }
}
