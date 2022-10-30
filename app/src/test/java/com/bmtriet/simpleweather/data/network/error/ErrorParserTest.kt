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
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorParserTest {

    private val errorParser = ErrorParser()

    @Test
    fun testParseErrorCode() {
        mapOf(
            401 to INVALID_API_KEY,
            404 to INVALID_CITY_ID,
            429 to QUOTE_EXCEED,
            500 to INTERNAL_SERVER,
            599 to INTERNAL_SERVER,
            600 to UNKNOWN_ERROR,
            0 to UNKNOWN_ERROR,
        ).forEach { (code, errorType) ->
            assertEquals(errorParser.parseError(code), errorType)
        }
    }

    @Test
    fun testParseErrorException() {
        mapOf(
            UnknownHostException() to NO_NETWORK,
            SocketTimeoutException() to TIMEOUT_ERROR,
            Exception() to UNKNOWN_ERROR,
        ).forEach { (code, errorType) ->
            assertEquals(errorParser.parseError(code), errorType)
        }
    }
}
