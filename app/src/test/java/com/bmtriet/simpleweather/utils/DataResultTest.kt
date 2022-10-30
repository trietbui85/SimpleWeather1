package com.bmtriet.simpleweather.utils

import com.bmtriet.simpleweather.data.network.error.ErrorType
import com.bmtriet.simpleweather.utils.DataResult.Error
import com.bmtriet.simpleweather.utils.DataResult.Success
import org.junit.Assert.assertEquals
import org.junit.Test

class DataResultTest {

    @Test
    fun testSuccessConstructor() {
        assertEquals(
            Success<String>(),
            Success(data = null),
        )
    }

    @Test
    fun testErrorConstructor() {
        assertEquals(
            Error(),
            Error(code = -1, message = ""),
        )
        assertEquals(
            Error(Exception("my exception")),
            Error(code = -1, message = "my exception"),
        )
        assertEquals(
            Error(ErrorType.TIMEOUT_ERROR),
            Error(
                code = ErrorType.TIMEOUT_ERROR.errorCode,
                message = ErrorType.TIMEOUT_ERROR.errorMessage,
            ),
        )
    }
}
