@file:JvmName("StringExt")

package com.bmtriet.simpleweather.utils

import kotlin.contracts.contract

fun CharSequence?.isNotNullAndNotEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullAndNotEmpty != null)
    }
    return !this.isNullOrEmpty()
}

/**
 * Create unique ID from a String.
 * - Must trim left & right whitespace.
 * - Ignore case
 */
fun String.generateUniqueId(): Int {
    return this.trim().uppercase().hashCode()
}
