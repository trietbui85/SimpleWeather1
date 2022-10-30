package com.bmtriet.simpleweather.data.network.error

import com.bmtriet.simpleweather.data.network.error.ErrorType.INTERNAL_SERVER
import com.bmtriet.simpleweather.data.network.error.ErrorType.INVALID_API_KEY
import com.bmtriet.simpleweather.data.network.error.ErrorType.INVALID_CITY_ID
import com.bmtriet.simpleweather.data.network.error.ErrorType.NO_NETWORK
import com.bmtriet.simpleweather.data.network.error.ErrorType.QUOTE_EXCEED
import com.bmtriet.simpleweather.data.network.error.ErrorType.TIMEOUT_ERROR
import com.bmtriet.simpleweather.data.network.error.ErrorType.UNKNOWN_ERROR
import org.junit.Assert.assertEquals
import org.junit.Test

class ErrorTypeTest {

    @Test
    fun testAllErrorType() {
        listOf(
            Triple(INVALID_API_KEY, 401, "Invalid API key"),
            Triple(INVALID_CITY_ID, 404, "City not found"),
            Triple(QUOTE_EXCEED, 429, "Too many request."),
            Triple(INTERNAL_SERVER, 500, "Internal Server Error"),
            Triple(NO_NETWORK, 990, "No internet connection"),
            Triple(TIMEOUT_ERROR, 991, "Timeout"),
            Triple(UNKNOWN_ERROR, -1, "Unknown Error"),
        ).forEach { (errorType, code, message) ->
            assertEquals(errorType.errorCode, code)
            assertEquals(errorType.errorMessage, message)
        }
    }
}
