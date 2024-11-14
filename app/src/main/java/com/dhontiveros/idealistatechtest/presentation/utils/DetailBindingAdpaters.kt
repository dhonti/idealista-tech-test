package com.dhontiveros.idealistatechtest.presentation.utils

import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem

@BindingAdapter("titleProperty")
fun bindTitlePropertyDetail(textView: TextView, item: PropertyDetail) {
    textView.text = textView.context.getTitleItem(item)
}

fun Context.getTitleItem(item: PropertyDetail): String = getString(
        R.string.property_title_detail,
        item.homeType.replaceFirstChar(Char::titlecase),
        item.operation
    )

@BindingAdapter("roomsProperty")
fun bindRoomsPropertyDetail(textView: TextView, item: PropertyDetail) {
    textView.text = textView.context.getString(
        R.string.property_rooms,
        item.moreCharacteristics.roomNumber.toString()
    )
}

@BindingAdapter("bathroomsProperty")
fun bindBathroomsPropertyDetail(textView: TextView, item: PropertyDetail) {
    textView.text = textView.context.getString(
        R.string.property_bathrooms,
        item.moreCharacteristics.bathNumber.toString()
    )
}

@BindingAdapter("surfaceProperty")
fun bindSurfacePropertyDetail(textView: TextView, item: PropertyDetail) {
    textView.text = textView.context.getString(
        R.string.property_surface,
        item.moreCharacteristics.constructedArea.toString()
    )
}