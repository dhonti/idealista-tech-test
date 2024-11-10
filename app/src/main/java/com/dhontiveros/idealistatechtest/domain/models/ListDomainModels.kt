package com.dhontiveros.idealistatechtest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Locale

@Parcelize
data class PropertyListItem(
    val propertyCode: String,
    val thumbnail: String,
    val floor: String,
    val price: Double,
    val priceInfo: PriceInfo,
    val propertyType: String,
    val operation: String,
    val size: Double,
    val exterior: Boolean,
    val rooms: Long,
    val bathrooms: Long,
    val address: String,
    val province: String,
    val municipality: String,
    val district: String,
    val country: String,
    val neighborhood: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val multimedia: Multimedia,
    val features: Features,
) : Parcelable {
    fun priceValue(): String = String.format(
        Locale.getDefault(),
        "%.3f %s",
        priceInfo.price.amount,
        priceInfo.price.currencySuffix
    )
}

@Parcelize
data class PriceInfo(
    val price: Price,
) : Parcelable

@Parcelize
data class Price(
    val amount: Double,
    val currencySuffix: String,
) : Parcelable

@Parcelize
data class Multimedia(
    val images: List<Image>,
) : Parcelable

@Parcelize
data class Image(
    val url: String,
    val tag: String,
) : Parcelable

@Parcelize
data class Features(
    val hasAirConditioning: Boolean,
    val hasBoxRoom: Boolean,
) : Parcelable