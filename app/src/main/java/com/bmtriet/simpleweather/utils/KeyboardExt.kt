@file:JvmName("KeyboardExt")

package com.bmtriet.simpleweather.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View?.hideKeyBoard(flags: Int = 0) {
    this ?: return

    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        hideSoftInputFromWindow(windowToken, flags)
    }
}

fun Activity?.hideKeyBoard(flags: Int = 0) {
    this ?: return
    currentFocus ?: return

    (getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        hideSoftInputFromWindow(currentFocus!!.windowToken, flags)
    }
}
