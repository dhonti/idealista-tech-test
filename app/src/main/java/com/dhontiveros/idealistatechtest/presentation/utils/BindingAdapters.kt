package com.dhontiveros.idealistatechtest.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun bindVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}