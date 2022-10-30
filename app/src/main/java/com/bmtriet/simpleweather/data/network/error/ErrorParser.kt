package com.bmtriet.simpleweather.data.network.error

import com.bmtriet.simpleweather.data.network.error.ErrorType.INTERNAL_SERVER
import com.bmtriet.simpleweather.data.network.error.ErrorType.INVALID_API_KEY
import com.bmtriet.simpleweather.data.network.error.ErrorType.INVALID_CITY_ID
import com.bmtriet.simpleweather.data.network.error.ErrorType.NO_NETWORK
import com.bmtriet.simpleweather.data.network.error.ErrorType.QUOTE_EXCEED
import com.bmtriet.simpleweather.data.network.error.ErrorType.TIMEOUT_ERROR
import com.bmtriet.simpleweather.data.network.error.ErrorType.UNKNOWN_ERROR
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorParser @Inject constructor() {
    /**
     * Parse the Http Status Code.
     * This method MUST only call when a Http request is not successful.
     */
    fun parseError(code: Int): ErrorType = when {
        code == INVALID_API_KEY.errorCode -> INVALID_API_KEY
        code == INVALID_CITY_ID.errorCode -> INVALID_CITY_ID
        code == QUOTE_EXCEED.errorCode -> QUOTE_EXCEED
        code >= INTERNAL_SERVER.errorCode && code <= MAXIMUM_5XX_CODE -> INTERNAL_SERVER
        else -> UNKNOWN_ERROR
    }

    fun parseError(e: Exception): ErrorType {
        return when (e) {
            is UnknownHostException -> NO_NETWORK
            is SocketTimeoutException -> TIMEOUT_ERROR
            else -> UNKNOWN_ERROR
        }
    }

    companion object {
        private const val MAXIMUM_5XX_CODE = 599
    }
}
