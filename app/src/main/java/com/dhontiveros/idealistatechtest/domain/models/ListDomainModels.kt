package com.dhontiveros.idealistatechtest.domain.models

import android.os.Parcelable
import com.dhontiveros.idealistatechtest.data.local.entity.PropertyLocalModel
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.Locale

@Parcelize
data class PropertyListItem(
    val propertyCode: String,
    val thumbnail: String,
    val floor: String? = null,
    val price: Double? = null,
    val priceInfo: PriceInfo,
    val propertyType: String,
    val operation: String? = null,
    val size: Double,
    val exterior: Boolean? = null,
    val rooms: Long,
    val bathrooms: Long,
    val address: String,
    val province: String,
    val municipality: String,
    val district: String,
    val country: String? = null,
    val neighborhood: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val description: String? = null,
    val multimedia: Multimedia? = null,
    val features: Features? = null,
    var isFavorite: Boolean = false
) : Parcelable {
    fun priceValue(): String = priceInfo.toValue()
    fun surfaceValue(): String {
        val formatter = NumberFormat.getNumberInstance(Locale.GERMANY).apply {
            maximumFractionDigits = 0
            isGroupingUsed = true
        }
        return formatter.format(this.size)
    }
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


fun PriceInfo.toValue(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.GERMANY).apply {
        maximumFractionDigits = 0
        isGroupingUsed = true
    }
    val priceNumber = formatter.format(this.price.amount)
    return String.format(
        Locale.getDefault(),
        "%s %s",
        priceNumber,
        this.price.currencySuffix
    )
}

fun PropertyListItem.toLocalData() = PropertyLocalModel(
    code = propertyCode,
    thumbnail = thumbnail,
    province = province,
    rooms = rooms,
    bathrooms = bathrooms,
    type = propertyType,
    district = district,
    address = address,
    municipality = municipality,
    size = size,
    amount = priceInfo.price.amount,
    currencySuffix = priceInfo.price.currencySuffix
)