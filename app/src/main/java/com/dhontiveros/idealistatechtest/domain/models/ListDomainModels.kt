package com.dhontiveros.idealistatechtest.domain.models

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
)


data class PriceInfo(
    val price: Price,
)

data class Price(
    val amount: Double,
    val currencySuffix: String,
)

data class Multimedia(
    val images: List<Image>,
)

data class Image(
    val url: String,
    val tag: String,
)

data class Features(
    val hasAirConditioning: Boolean,
    val hasBoxRoom: Boolean,
)