package com.bmtriet.simpleweather.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.bmtriet.simpleweather.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

class StringHolderKtTest {

    @Test
    fun testNativeStringHolder() {
        assertEquals(NativeStringHolder(), NativeStringHolder(text = null))
    }

    @Test
    fun testAndroidStringHolder() {
        assertEquals(AndroidStringHolder(), AndroidStringHolder(resId = 0))
    }

    @Test
    fun testAndroidArgStringHolder() {
        assertEquals(
            AndroidArgStringHolder(),
            AndroidArgStringHolder(resId = 0, resArgs = emptyArray()),
        )
    }
}

@RunWith(RobolectricTestRunner::class)
class GetHolderTextTest {

    private val context = ApplicationProvider.getApplicationContext() as Context

    @Test
    fun getHolderText_NativeStringHolder() {
        assertEquals(
            NativeStringHolder(text = "text").getHolderText(context),
            "text",
        )
    }

    @Test
    fun getHolderText_AndroidStringHolder() {
        assertEquals(
            AndroidStringHolder(resId = R.string.test_string_0_param).getHolderText(context),
            "Text.",
        )
    }

    @Test
    fun getHolderText_AndroidArgStringHolder() {
        assertEquals(
            AndroidArgStringHolder(
                resId = R.string.test_string_1_param,
                resArgs = arrayOf("1st"),
            ).getHolderText(context),
            "Text 1st.",
        )
        assertEquals(
            AndroidArgStringHolder(
                resId = R.string.test_string_2_param,
                resArgs = arrayOf("1st", 2),
            ).getHolderText(context),
            "Text 1st (2).",
        )
    }
}
