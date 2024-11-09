package com.dhontiveros.idealistatechtest.core.extensions

import android.view.View
import android.widget.TextView

fun TextView.goneIfTextEmpty() {
    visibility = if (text.isNullOrEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

/**
 * Implementation of lazy that is not thread safe. Useful when you know what thread you will be
 * executing on and are not worried about synchronization.
 */
fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}