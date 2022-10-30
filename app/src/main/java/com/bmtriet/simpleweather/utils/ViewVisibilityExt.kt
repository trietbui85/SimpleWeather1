@file:JvmName("ViewVisibilityExt")

package com.bmtriet.simpleweather.utils

import android.view.View

inline fun View?.beVisibleOtherwiseInvisible(crossinline block: () -> Boolean): Boolean {
    if (this == null) return false
    return if (block()) {
        visibility = View.VISIBLE
        true
    } else {
        visibility = View.INVISIBLE
        false
    }
}

inline fun View?.beInvisible(crossinline block: () -> Boolean): Boolean {
    if (this == null) return false

    if (block()) {
        visibility = View.INVISIBLE
        return true
    }
    return false
}

inline fun View?.beVisible(crossinline block: () -> Boolean): Boolean {
    if (this == null) return false

    if (block()) {
        visibility = View.VISIBLE
        return true
    }
    return false
}
