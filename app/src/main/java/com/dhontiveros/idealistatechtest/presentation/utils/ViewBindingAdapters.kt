package com.dhontiveros.idealistatechtest.presentation.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem

@BindingAdapter("visible")
fun bindVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

