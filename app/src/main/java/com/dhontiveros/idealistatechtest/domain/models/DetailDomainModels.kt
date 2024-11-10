package com.dhontiveros.idealistatechtest.domain.models

import java.util.Locale

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
) {
    fun adidValue(): String = adid.toString()

    fun priceValue(): String = String.format(
        Locale.getDefault(),
        "%.3f %s",
        priceInfoDetail.amount,
        priceInfoDetail.currencySuffix
    )
}

data class EnergyCertification(
    val title: String,
    val energyConsumption: EnergyConsumption,
    val emissions: Emissions
)

data class EnergyConsumption(
    val type: String
)

data class Emissions(
    val type: String
)

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
)

data class MultimediaDetail(
    val images: List<ImageDetail>
)

data class ImageDetail(
    val url: String,
    val tag: String,
    val localizedName: String,
    val multimediaID: Long
)

data class PriceInfoDetail(
    val amount: Double,
    val currencySuffix: String
)

data class Ubication(
    val latitude: Double,
    val longitude: Double
)
