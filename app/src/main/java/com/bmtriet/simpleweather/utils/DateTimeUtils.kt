package com.bmtriet.simpleweather.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Suppress("MagicNumber")
object DateTimeUtils {
    /**
     * Convert a Unit timestamp to an Integer YYYYMMDD.
     * For example:
     * - 1666877423 is Thursday, 27 October 2022 13:30:23 GMT+0
     * - Will be converted to 20221017
     */
    fun toYyyyMmDd(dateMs: Long): Int {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.timeInMillis = dateMs * 1_000
        return calendar[Calendar.YEAR] * 10_000 +
            (calendar[Calendar.MONTH] + 1) * 100 +
            calendar[Calendar.DATE]
    }

    /**
     * Parse a Long Date to String Date to show in ForecastViewHolder.
     * For example 1666877423 -> Thu, 27 Oct 2022
     */
    fun toSimpleDateText(dateMs: Long): String {
        val simpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US)
        return try {
            simpleDateFormat.format(Date(dateMs * 1_000))
        } catch (e: Exception) {
            simpleDateFormat.format(Date())
        }
    }

    fun getCurrentTimeMs(): Long = System.currentTimeMillis()
}
