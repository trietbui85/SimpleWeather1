package com.bmtriet.simpleweather.testutils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun ViewInteraction?.checkViewVisible() {
    this ?: return
    check(matches(isDisplayed()))
}

fun ViewInteraction?.checkViewInvisible() {
    this ?: return
    check(matches(not(isDisplayed())))
}

fun ViewInteraction?.checkViewGone() {
    this ?: return
    check(ViewAssertions.doesNotExist())
}

fun ViewInteraction?.checkViewHasHint(@StringRes resId: Int) {
    this ?: return
    matches(withHint(resId))
}

fun ViewInteraction?.checkViewHasText(@StringRes resId: Int) {
    this ?: return
    matches(withText(resId))
}

fun ViewInteraction?.checkViewHasText(text: String) {
    this ?: return
    matches(withText(text))
}

fun ViewInteraction?.checkViewHasSrcDrawable(@DrawableRes id: Int) {
    this ?: return
    matches(withDrawable(id))
}

fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("ImageView with drawable same as drawable with id $id")
    }

    override fun matchesSafely(view: View): Boolean {
        val context = view.context
        val expectedBitmap = context.getDrawable(id)!!.toBitmap()

        return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
    }
}
