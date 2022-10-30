package com.bmtriet.simpleweather.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolderWithLayout(
    parent: ViewGroup,
    @LayoutRes layoutResId: Int,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false),
) {

    fun setViewHolderText(textView: TextView?, stringHolder: StringHolder) {
        textView.setHolderText(stringHolder)
    }
}
