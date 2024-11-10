package com.dhontiveros.idealistatechtest.core.extensions

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dhontiveros.idealistatechtest.domain.exceptions.NotificationData
import com.dhontiveros.idealistatechtest.presentation.base.BaseActivity
import com.dhontiveros.idealistatechtest.presentation.common.AppDialog

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

// Fragment region
fun Fragment.showNotification(data: NotificationData?) =
    AppDialog(requireContext(), data).create().show()

fun Fragment.showLoader(msg: String? = null) = (activity as? BaseActivity<*>)?.showLoader(msg)

fun Fragment.hideLoader() = (activity as? BaseActivity<*>)?.hideLoader()
