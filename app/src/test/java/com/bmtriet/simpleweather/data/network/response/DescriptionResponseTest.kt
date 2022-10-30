package com.bmtriet.simpleweather.data.network.response

import org.junit.Assert.assertEquals
import org.junit.Test

class DescriptionResponseTest {

    @Test
    fun testConstructor() {
        assertEquals(
            DescriptionResponse(),
            DescriptionResponse(
                description = null,
            ),
        )
    }
}
