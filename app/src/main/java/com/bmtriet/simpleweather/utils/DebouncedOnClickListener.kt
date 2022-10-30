package com.bmtriet.simpleweather.utils

/**
 * Created by khine.thandarmyo@zalora.com on 19/1/22.
 */
import android.os.Handler
import android.os.Looper
import android.view.View

// For composable debounce button click
class DebouncedClickCallback(val delay: Long = 350) {
    var enabled = true
    private val main: Handler = Handler(Looper.getMainLooper())
    private val enableAgain = Runnable { enabled = true }

    fun onClick(customDelay: Long = delay, doClick: () -> Unit) {
        if (enabled) {
            enabled = false

            // Post to the main looper directly rather than going through the view.
            // Ensure that ENABLE_AGAIN will be executed, avoid static field {@link #enabled}
            // staying in false state.
            main.postDelayed(enableAgain, customDelay)

            doClick()
        }
    }
}

class DebouncedOnClickListener(
    private val intervalMillis: Long = 300,
    private val doClick: ((View) -> Unit),
) : View.OnClickListener {

    override fun onClick(v: View) {
        if (enabled) {
            enabled = false
            v.postDelayed(ENABLE_AGAIN, intervalMillis)
            doClick(v)
        }
    }

    companion object {
        @JvmStatic
        var enabled = true
        private val ENABLE_AGAIN =
            Runnable { enabled = true }
    }
}

fun View.setOnSafeClick(intervalMillis: Long? = null, doClick: (View) -> Unit) {
    if (intervalMillis != null) {
        setOnClickListener(
            DebouncedOnClickListener(
                intervalMillis = intervalMillis,
                doClick = doClick,
            ),
        )
    } else {
        setOnClickListener(DebouncedOnClickListener(doClick = doClick))
    }
}
