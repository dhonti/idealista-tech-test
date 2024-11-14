package com.dhontiveros.idealistatechtest.presentation.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("titleProperty")
fun bindTitleProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_title,
        item.propertyType.replaceFirstChar(Char::titlecase),
        item.address
    )
}

@BindingAdapter("locationProperty")
fun bindLocationProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_location,
        item.district,
        item.municipality,
        item.province
    )
}

@BindingAdapter("roomsProperty")
fun bindRoomsProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_rooms,
        item.rooms.toString()
    )
}

@BindingAdapter("bathroomsProperty")
fun bindBathroomsProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_bathrooms,
        item.bathrooms.toString()
    )
}

@BindingAdapter("surfaceProperty")
fun bindSurfaceProperty(textView: TextView, item: PropertyListItem) {
    textView.text = textView.context.getString(
        R.string.property_surface,
        item.surfaceValue()
    )
}

@BindingAdapter("favoriteProperty")
fun bindSurfaceProperty(imageView: ImageView, item: PropertyListItem) {
    imageView.setImageResource(
        if (item.isFavorite) {
            R.drawable.ic_fav
        } else {
            R.drawable.ic_no_fav
        }
    )
}

@BindingAdapter("dateFavProperty")
fun bindDateFavProperty(textView: TextView, item: PropertyListItem){
    textView.text = item.dateFav?.toDateString().orEmpty()
}

fun Long.toDateString(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    val date = Date(this)
    return sdf.format(date)
}