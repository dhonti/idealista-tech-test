package com.dhontiveros.idealistatechtest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.NumberFormat
import java.util.Locale

@Parcelize
data class PropertyDetail(
    val adid: Long,
    val price: Double,
    val priceInfoDetail: PriceInfoDetail,
    val operation: String,
    val propertyType: String,
    val extendedPropertyType: String,
    val homeType: String,
    val state: String,
    val multimedia: MultimediaDetail,
    val propertyComment: String,
    val ubication: Ubication,
    val country: String,
    val moreCharacteristics: MoreCharacteristics,
    val energyCertification: EnergyCertification
) : Parcelable {
    fun priceValue(): String = priceInfoDetail.toValue()
}

@Parcelize
data class EnergyCertification(
    val title: String,
    val energyConsumption: EnergyConsumption,
    val emissions: Emissions
) : Parcelable

@Parcelize
data class EnergyConsumption(
    val type: String
) : Parcelable

@Parcelize
data class Emissions(
    val type: String
) : Parcelable

@Parcelize
data class MoreCharacteristics(
    val communityCosts: Double,
    val roomNumber: Int,
    val bathNumber: Int,
    val exterior: Boolean,
    val housingFurnitures: String,
    val agencyIsABank: Boolean,
    val energyCertificationType: String,
    val flatLocation: String,
    val modificationDate: Long,
    val constructedArea: Long,
    val lift: Boolean,
    val boxroom: Boolean,
    val isDuplex: Boolean,
    val floor: String,
    val status: String
) : Parcelable

@Parcelize
data class MultimediaDetail(
    val images: List<ImageDetail>
) : Parcelable

@Parcelize
data class ImageDetail(
    val url: String,
    val tag: String,
    val localizedName: String,
    val multimediaID: Long
) : Parcelable

@Parcelize
data class PriceInfoDetail(
    val amount: Double,
    val currencySuffix: String
) : Parcelable

@Parcelize
data class Ubication(
    val latitude: Double,
    val longitude: Double
) : Parcelable

fun PriceInfoDetail.toValue(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.GERMANY).apply {
        maximumFractionDigits = 0
        isGroupingUsed = true
    }
    val priceNumber = formatter.format(this.amount)
    return String.format(
        Locale.getDefault(),
        "%s %s",
        priceNumber,
        this.currencySuffix
    )
}