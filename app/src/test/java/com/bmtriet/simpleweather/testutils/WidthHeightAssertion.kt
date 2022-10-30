package com.bmtriet.simpleweather.testutils

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import org.junit.Assert.assertEquals

/**
 * Assert that View has width as a dimension like R.dimen.xxx (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasWidthDimen(@DimenRes sizeRes: Int, message: String? = null) {
    val size = context.resources.getDimensionPixelSize(sizeRes)
    val width = this.layoutParams.width
    val msg = message
        ?: "View (${getIdText()}) width is expected to ${size}px but it's ${width}px"
    assertEquals(msg, width, size)
}

/**
 * Assert that View has width as match_parent
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasWidthMatchParent(message: String? = null) {
    val width = this.layoutParams.width
    val msg = message
        ?: "View (${getIdText()}) width is expected to match_parent but it's ${width}px"
    assertEquals(msg, width, ViewGroup.LayoutParams.MATCH_PARENT)
}

/**
 * Assert that View has width as wrap_content
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasWidthWrapContent(message: String? = null) {
    val width = this.layoutParams.width
    val msg = message
        ?: "View (${getIdText()}) width is expected to wrap_content but it's ${width}px"
    assertEquals(msg, width, ViewGroup.LayoutParams.WRAP_CONTENT)
}

/**
 * Assert that View has width as an Int value like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasWidthInt(sizeInt: Int, message: String? = null) {
    val size = sizeInt * Resources.getSystem().displayMetrics.density.toInt()
    val width = this.layoutParams.width
    val msg = message
        ?: "View (${getIdText()}) width is expected to ${sizeInt}dp (${size}px) but it's ${width}px"
    assertEquals(msg, width, size)
}

/**
 * Assert that View has height as a dimension like R.dimen.xxx (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasHeightDimen(@DimenRes sizeRes: Int, message: String? = null) {
    val size = context.resources.getDimensionPixelSize(sizeRes)
    val height = this.layoutParams.height
    val msg = message
        ?: "View (${getIdText()}) height is expected to ${size}px but it's ${height}px"
    assertEquals(msg, height, size)
}

/**
 * Assert that View has height as match_parent
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasHeightMatchParent(message: String? = null) {
    val height = this.layoutParams.height
    val msg = message
        ?: "View (${getIdText()}) height is expected to match_parent but it's ${height}px"
    assertEquals(msg, height, ViewGroup.LayoutParams.MATCH_PARENT)
}

/**
 * Assert that View has height as wrap_content
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasHeightWrapContent(message: String? = null) {
    val height = this.layoutParams.height
    val msg = message
        ?: "View (${getIdText()}) height is expected to wrap_content but it's ${height}px"
    assertEquals(msg, height, ViewGroup.LayoutParams.WRAP_CONTENT)
}

/**
 * Assert that View has height as an Int value like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasHeightInt(sizeInt: Int, message: String? = null) {
    val size = sizeInt * Resources.getSystem().displayMetrics.density.toInt()
    val height = this.layoutParams.height
    val msg = message ?: """View (${getIdText()}) height is expected to ${sizeInt}dp (${size}px)
            | but it's ${height}px
    """.trimMargin()
    assertEquals(msg, height, size)
}

/**
 * Assert that View has width & height as a dimension like R.dimen.xxx (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasWidthHeightDimen(
    @DimenRes widthRes: Int,
    @DimenRes heightRes: Int,
    message: String? = null,
) {
    this.assertHasWidthDimen(widthRes, message)
    this.assertHasHeightDimen(heightRes, message)
}

/**
 * Assert that View has width & height as an Int value like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasWidthHeightInt(widthInt: Int, heightInt: Int, message: String? = null) {
    this.assertHasWidthInt(widthInt, message)
    this.assertHasHeightInt(heightInt, message)
}

/**
 * Assert that View has width & height as an Int value like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasWidthHeightWrapContent(message: String? = null) {
    this.assertHasWidthWrapContent(message)
    this.assertHasHeightWrapContent(message)
}

/**
 * Assert that View has width & height as an Int value like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun View.assertHasWidthMatchParentHeightWrapContent(message: String? = null) {
    this.assertHasWidthMatchParent(message)
    this.assertHasHeightWrapContent(message)
}
