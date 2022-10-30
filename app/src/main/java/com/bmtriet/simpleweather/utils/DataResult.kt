package com.bmtriet.simpleweather.utils

import com.bmtriet.simpleweather.data.network.error.ErrorType

sealed class DataResult<out R> {

    data class Success<out T>(val data: T? = null) : DataResult<T>()
    data class Error(
        val code: Int = -1,
        val message: String = "",
    ) : DataResult<Nothing>() {
        constructor(e: Exception) : this(-1, e.message.orEmpty())
        constructor(err: ErrorType) : this(err.errorCode, err.errorMessage)
    }
}
