package com.dhontiveros.idealistatechtest.presentation.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import java.util.Locale

@BindingAdapter("visible")
fun bindVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("titleProperty")
fun bindTitleProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_title,
        item.propertyType.replaceFirstChar(Char::titlecase),
        item.address)
}

@BindingAdapter("locationProperty")
fun bindLocationProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_location,
        item.district,
        item.municipality,
        item.province)
}

@BindingAdapter("roomsProperty")
fun bindRoomsProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_rooms,
        item.rooms.toString())
}

@BindingAdapter("bathroomsProperty")
fun bindBathroomsProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_bathrooms,
        item.bathrooms.toString())
}

@BindingAdapter("surfaceProperty")
fun bindSurfaceProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_surface,
        item.surfaceValue())
}