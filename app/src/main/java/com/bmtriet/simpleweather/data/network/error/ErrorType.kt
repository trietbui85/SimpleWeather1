package com.bmtriet.simpleweather.data.network.error

/**
 * Refer to https://openweathermap.org/faq > API errors
 */
@Suppress("MagicNumber")
enum class ErrorType(val errorCode: Int, val errorMessage: String) {
    INVALID_API_KEY(401, "Invalid API key"),
    INVALID_CITY_ID(404, "City not found"),
    QUOTE_EXCEED(429, "Too many request."),
    INTERNAL_SERVER(500, "Internal Server Error"),
    NO_NETWORK(990, "No internet connection"),
    TIMEOUT_ERROR(991, "Timeout"),
    UNKNOWN_ERROR(-1, "Unknown Error"),
}
