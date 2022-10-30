package com.bmtriet.simpleweather.testutils

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import org.junit.Assert.assertEquals

/**
 * Assert that View has Padding Start value as a Int like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasPaddingStart(paddingStartInt: Int, message: String? = null) {
    verifyMarginPadding("paddingStart", getIdText(), paddingStartInt, paddingStart, message)
}

/**
 * Assert that View has Padding End value as a Int like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasPaddingEnd(paddingEndInt: Int, message: String? = null) {
    verifyMarginPadding("paddingEnd", getIdText(), paddingEndInt, paddingEnd, message)
}

/**
 * Assert that View has Padding Top value as a Int like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasPaddingTop(paddingTopInt: Int, message: String? = null) {
    verifyMarginPadding("paddingTop", getIdText(), paddingTopInt, paddingTop, message)
}

/**
 * Assert that View has Padding Bottom value as a Int like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasPaddingBottom(paddingBottomInt: Int, message: String? = null) {
    verifyMarginPadding("paddingBottom", getIdText(), paddingBottomInt, paddingBottom, message)
}

/**
 * Assert that View has Margin Start value as a Int like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasMarginStart(marginStartInt: Int, message: String? = null) {
    verifyMarginPadding(
        "marginStart",
        getIdText(),
        marginStartInt,
        (layoutParams as ViewGroup.MarginLayoutParams).marginStart,
        message,
    )
}

/**
 * Assert that View has Margin End value as a Int like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasMarginEnd(marginEndInt: Int, message: String? = null) {
    verifyMarginPadding(
        "marginEnd",
        getIdText(),
        marginEndInt,
        (layoutParams as ViewGroup.MarginLayoutParams).marginEnd,
        message,
    )
}

/**
 * Assert that View has Margin Top value as a Int like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasMarginTop(marginTopInt: Int, message: String? = null) {
    verifyMarginPadding(
        "marginTop",
        getIdText(),
        marginTopInt,
        (layoutParams as ViewGroup.MarginLayoutParams).topMargin,
        message,
    )
}

/**
 * Assert that View has Margin Bottom value as a Int like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasMarginBottom(marginBottomInt: Int, message: String? = null) {
    verifyMarginPadding(
        "marginBottom",
        getIdText(),
        marginBottomInt,
        (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin,
        message,
    )
}

private fun verifyMarginPadding(
    from: String,
    viewId: String,
    oriSpaceInt: Int,
    realSpacePx: Int,
    message: String? = null,
) {
    val oriSpacePx = oriSpaceInt * Resources.getSystem().displayMetrics.density.toInt()
    val msg = message ?: """View ($viewId $from is expected to ${oriSpaceInt}dp 
        |(${oriSpacePx}px) but it's ${realSpacePx}px
    """.trimMargin()
    assertEquals(msg, realSpacePx, oriSpacePx)
}
