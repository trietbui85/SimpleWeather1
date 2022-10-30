package com.bmtriet.simpleweather.utils

import com.bmtriet.simpleweather.utils.DateTimeUtils.toSimpleDateText
import com.bmtriet.simpleweather.utils.DateTimeUtils.toYyyyMmDd
import org.junit.Assert.assertEquals
import org.junit.Test

class DateTimeUtilsKtTest {

    @Test
    fun testLongToYyyyMmDd() {
        // 0 -> 1 January 1970 -> 19700000
        assertEquals(toYyyyMmDd(0L), 19700101)

        // 1666877423 -> 27 October 2022 -> 20221027
        assertEquals(toYyyyMmDd(1666877423L), 20221027)
    }

    @Test
    fun testToSimpleDateText() {
        // 1666877423 -> Thu, 27 Oct 2022
        assertEquals(toSimpleDateText(1666877423L), "Thu, 27 Oct 2022")
    }
}
