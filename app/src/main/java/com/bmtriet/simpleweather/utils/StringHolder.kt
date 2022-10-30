package com.bmtriet.simpleweather.utils

import android.content.Context
import android.widget.TextView
import androidx.annotation.StringRes

sealed interface StringHolder

data class NativeStringHolder(
    val text: String? = null,
) : StringHolder

data class AndroidStringHolder(
    @StringRes val resId: Int = 0,
) : StringHolder

data class AndroidArgStringHolder(
    @StringRes val resId: Int = 0,
    val resArgs: Array<Any> = emptyArray(),
) : StringHolder {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AndroidArgStringHolder

        if (resId != other.resId) return false
        if (!resArgs.contentEquals(other.resArgs)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = resId
        result = 31 * result + resArgs.contentHashCode()
        return result
    }
}

fun StringHolder.getHolderText(context: Context): String = when {
    this is NativeStringHolder -> text.orEmpty()
    this is AndroidStringHolder -> {
        if (resId <= 0) "" else context.getString(resId)
    }
    this is AndroidArgStringHolder -> {
        if (resId <= 0) "" else context.getString(this.resId, *this.resArgs)
    }
    else -> ""
}

fun TextView?.setHolderText(stringHolder: StringHolder) {
    this ?: return
    text = stringHolder.getHolderText(context)
}
