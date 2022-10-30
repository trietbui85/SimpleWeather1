package com.bmtriet.simpleweather.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtTest {

    @Test
    fun testIsNotNullAndNotEmpty() {
        mapOf(
            null to false,
            "" to false,
            "valid" to true,
        ).forEach { (text, isValid) ->
            assertEquals(text.isNotNullAndNotEmpty(), isValid)
        }
    }

    @Test
    fun testGenerateUniqueId() {
        val oriText = "openweathermap"
        val hashCode = oriText.uppercase().hashCode()
        listOf(
            "openweathermap",
            "  openweathermap",
            "  openweathermap  ",
            "  Openweathermap  ",
            "  OPENWEATHERMAP  ",
        ).forEach { text ->
            assertEquals(text.generateUniqueId(), hashCode)
        }
    }
}
