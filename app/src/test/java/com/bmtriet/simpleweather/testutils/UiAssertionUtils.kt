@file:Suppress("MagicNumber")

package com.bmtriet.simpleweather.testutils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import org.robolectric.Shadows

// https://github.com/dbottillo/Blog/blob/espresso_match_imageview/app/src/androidTest/java/com/danielebottillo/blog/config/DrawableMatcher.java
// There is no built-in method to compare 2 Drawable, so compare its Bitmap instead
fun compare2Drawable(drawable1: Drawable?, drawable2: Drawable?): Boolean {
    return if (drawable1 == null && drawable2 == null) {
        true
    } else if (drawable1 == null) {
        false
    } else if (drawable2 == null) {
        false
    } else {
        getBitmap(drawable1).sameAs(getBitmap(drawable2))
    }
}

private fun getBitmap(drawable: Drawable): Bitmap {
    val bitmap: Bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888,
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

// Get the View visibility in String format.
fun View.getVisibilityText(): String = when (this.visibility) {
    View.VISIBLE -> "VISIBLE"
    View.INVISIBLE -> "INVISIBLE"
    else -> "GONE"
}

// Get the View state (enable/disable) in String format.
fun View.getEnableStateText(): String = if (this.isEnabled) "ENABLED" else "DISABLED"

// Get the class name of View in String format.
fun View.getClassName(): String = this::class.java.simpleName

// Convert ColorInt format (like Color.BLACK or -1979711488) into String ("#000000")
fun colorIntToString(@ColorInt colorInt: Int): String {
    return java.lang.String.format("#%06X", 0xFFFFFF and colorInt)
}

// Get the View ID in String format, like: android.R.string.content, or app.R.string.button
// This method is borrow from View.toString()
fun View.getIdText(): String {
    var strId = "NO_ID"
    val resources: Resources = this.resources

    // DROID-7096 Lint will throw warning when comparing resource types other than equality
    // Can suppress it to execute the logic here
    @SuppressLint("ResourceType")
    if (id != View.NO_ID && id > 0) {
        try {
            val pkgName: String = when (id and -0x1000000) {
                0x7f000000 -> "app"
                0x01000000 -> "android"
                else -> resources.getResourcePackageName(id)
            }
            strId = pkgName
            strId += ".R."
            strId += resources.getResourceTypeName(id)
            strId += "."
            strId += resources.getResourceEntryName(id)
        } catch (e: Resources.NotFoundException) {
            println(e.message)
        }
    }
    return strId
}

// https://stackoverflow.com/questions/18008044/assert-imageview-was-loaded-with-specific-drawable-resource-id
@DrawableRes
fun Drawable.getResId(): Int {
    return Shadows.shadowOf(this).createdFromResId
}
