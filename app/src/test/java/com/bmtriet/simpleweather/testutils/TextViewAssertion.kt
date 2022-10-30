@file:Suppress("MagicNumber")

package com.bmtriet.simpleweather.testutils

import android.content.res.Resources
import android.graphics.Color
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import org.junit.Assert.assertEquals
import org.junit.Assert.fail

/**
 * Note: these assertion utilities are used for UI test only.
 */
private const val H3_TEXT_SIZE = 32 // 32sp
private const val H5_TEXT_SIZE = 20 // 20sp
private const val H6_TEXT_SIZE = 18 // 18sp
private const val H7_TEXT_SIZE = 16 // 16sp
private const val CAPTION_TEXT_SIZE = 14 // 14sp
private const val MICRO_TEXT_SIZE = 12 // 12sp

/**
 * Assert that TextView's Appearance is H3 (textSize = 32sp, lineHeight = 48sp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextAppearanceH3(message: String? = null) {
    this.assertHasTextSizeInt(H3_TEXT_SIZE, message)
    // Only verify for textSize. About lineHeight, will verify later when we have this method.
}

/**
 * Assert that TextView's Appearance is H5 (textSize = 20sp, lineHeight = 30sp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextAppearanceH5(message: String? = null) {
    this.assertHasTextSizeInt(H5_TEXT_SIZE, message)
    // Only verify for textSize. About lineHeight, will verify later when we have this method.
}

/**
 * Assert that TextView's Appearance is H6 (textSize = 18sp, lineHeight = 26sp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextAppearanceH6(message: String? = null) {
    this.assertHasTextSizeInt(H6_TEXT_SIZE, message)
    // Only verify for textSize. About lineHeight, will verify later when we have this method.
}

/**
 * Assert that TextView's Appearance is H7 (textSize = 16sp, lineHeight = 24sp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextAppearanceH7(message: String? = null) {
    this.assertHasTextSizeInt(H7_TEXT_SIZE, message)
    // Only verify for textSize. About lineHeight, will verify later when we have this method.
}

/**
 * Assert that TextView's Appearance is Caption (textSize = 14sp, lineHeight = 20sp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextAppearanceCaption(message: String? = null) {
    this.assertHasTextSizeInt(CAPTION_TEXT_SIZE, message)
    // Only verify for textSize. About lineHeight, will verify later when we have this method.
}

/**
 * Assert that TextView's Appearance is Micro (textSize = 12sp, lineHeight = 18sp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextAppearanceMicro(message: String? = null) {
    this.assertHasTextSizeInt(MICRO_TEXT_SIZE, message)
    // Only verify for textSize. About lineHeight, will verify later when we have this method.
}

/**
 * Assert that TextView has text color R.color.xxx.
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextColorRes(@ColorRes colorRes: Int, message: String? = null) {
    val color = ContextCompat.getColor(context, colorRes)
    val msg = message ?: """TextView (${getIdText()}) color is expected to $color
        | but it's ${colorIntToString(currentTextColor)} ($currentTextColor)"""
        .trimMargin()
    assertEquals(msg, currentTextColor, color)
}

/**
 * Assert that TextView has text color, with input like "#RRGGBB" or #AARRGGBB"
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextColorString(colorString: String, message: String? = null) {
    try {
        val color = Color.parseColor(colorString)
        val msg = message ?: """TextView (${getIdText()}) color is expected to $colorString
            | but it's ${colorIntToString(currentTextColor)} ($currentTextColor)"""
            .trimMargin()
        assertEquals(msg, currentTextColor, color)
    } catch (e: Exception) {
        val msg = message ?: "TextView (${getIdText()}) color ($colorString) is not a valid color"
        fail(msg)
    }
}

/**
 * Assert that TextView has text content as a String.
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextContent(textStr: String, message: String? = null) {
    val msg = message
        ?: "TextView (${getIdText()}) text is expected to \"$textStr\" but it's \"$text\""
    assertEquals(msg, text, textStr)
}

/**
 * Assert that TextView has text content as a ID like R.string.xxx.
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextContent(@StringRes textRes: Int, message: String? = null) {
    val textStr = context.getString(textRes)
    val msg = message
        ?: "TextView (${getIdText()}) text is expected to \"$textStr\" but it's \"$text\""
    assertEquals(msg, text, textStr)
}

/**
 * Assert that TextView has text size as a dimension like R.dimen.xxx (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextSizeDimen(@DimenRes sizeRes: Int, message: String? = null) {
    val size = context.resources.getDimension(sizeRes)
    val msg = message
        ?: "TextView (${getIdText()}) text size is expected to ${size}px but it's ${textSize}px"
    assertEquals(msg, textSize, size)
}

/**
 * Assert that TextView has text size as an Int value like 16 (unit is dp)
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasTextSizeInt(sizeInt: Int, message: String? = null) {
    val size = (sizeInt * Resources.getSystem().displayMetrics.scaledDensity + 0.5).toInt()
    val msg = message
        ?: """TextView (${getIdText()}) text size
            |is expected to ${sizeInt}sp (${size}px) but it's ${textSize}px
        """.trimMargin()
    assertEquals(msg, textSize, size)
}

/**
 * Assert that TextView has maxLines
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasMaxLines(lines: Int, message: String? = null) {
    val msg =
        message ?: "TextView (${getIdText()}) maxLines is expected to $lines but it's $maxLines"
    assertEquals(msg, maxLines, lines)
}

/**
 * Assert that TextView has lineCount
 *
 * @param message: The assertion message, or null to use default assertion message.
 */
fun TextView.assertHasLineCount(linesCount: Int, message: String? = null) {
    val msg = message
        ?: "TextView (${getIdText()}) lineCount is expected to $linesCount but it's $lineCount"
    assertEquals(msg, lineCount, linesCount)
}
