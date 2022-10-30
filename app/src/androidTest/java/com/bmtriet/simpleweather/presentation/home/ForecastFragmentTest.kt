package com.bmtriet.simpleweather.presentation.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.bmtriet.simpleweather.R
import com.bmtriet.simpleweather.testutils.checkViewHasHint
import com.bmtriet.simpleweather.testutils.checkViewHasSrcDrawable
import com.bmtriet.simpleweather.testutils.checkViewHasText
import com.bmtriet.simpleweather.testutils.checkViewInvisible
import com.bmtriet.simpleweather.testutils.checkViewVisible
import com.bmtriet.simpleweather.testutils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@MediumTest
class ForecastFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val edtCity by lazy {
        onView(withId(R.id.edtCity))
    }
    private val btnClear by lazy {
        onView(withId(R.id.btnClear))
    }
    private val btnGetWeather by lazy {
        onView(withId(R.id.btnGetWeather))
    }

    @Test
    fun testEdtCity_UI() {
        launchFragmentInHiltContainer<ForecastFragment>()

        onView(withId(R.id.edtCity))?.run {
            checkViewVisible()
            checkViewHasHint(R.string.enter_city_name)
            checkViewHasText("")
        }
    }

    @Test
    fun testBtnClear_UI() {
        launchFragmentInHiltContainer<ForecastFragment>()

        btnClear?.run {
            checkViewInvisible()
            checkViewHasSrcDrawable(android.R.drawable.ic_menu_close_clear_cancel)
        }
    }

    @Test
    fun testBtnGetWeather_UI() {
        launchFragmentInHiltContainer<ForecastFragment>()

        btnGetWeather?.run {
            checkViewVisible()
            checkViewHasText(R.string.btn_get_weather)
        }
    }

    @Test
    fun testEmptyView_UI() {
        launchFragmentInHiltContainer<ForecastFragment>()

        onView(withId(R.id.emptyView))?.run {
            checkViewInvisible()
            checkViewHasText(R.string.no_result)
        }
    }

    @Test
    fun testErrorView_UI() {
        launchFragmentInHiltContainer<ForecastFragment>()

        onView(withId(R.id.errorView))?.run {
            checkViewInvisible()
            checkViewHasText("")
        }
    }

    @Test
    fun enterTextToEditTextThenWillShowButtonClear() {
        launchFragmentInHiltContainer<ForecastFragment>()

        edtCity.perform(ViewActions.typeText("a"))
        btnClear.checkViewVisible()
    }
}
