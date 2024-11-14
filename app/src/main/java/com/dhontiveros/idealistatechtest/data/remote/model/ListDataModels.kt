package com.dhontiveros.idealistatechtest.data.remote.model

import com.dhontiveros.idealistatechtest.domain.models.Features
import com.dhontiveros.idealistatechtest.domain.models.Image
import com.dhontiveros.idealistatechtest.domain.models.Multimedia
import com.dhontiveros.idealistatechtest.domain.models.Price
import com.dhontiveros.idealistatechtest.domain.models.PriceInfo
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PropertyListItemDto(
    @Json(name = "propertyCode") val propertyCode: String,
    @Json(name = "thumbnail") val thumbnail: String,
    @Json(name = "floor") val floor: String,
    @Json(name = "price") val price: Double,
    @Json(name = "priceInfo") val priceInfo: PriceInfoDto,
    @Json(name = "propertyType") val propertyType: String,
    @Json(name = "operation") val operation: String,
    @Json(name = "size") val size: Double,
    @Json(name = "exterior") val exterior: Boolean,
    @Json(name = "rooms") val rooms: Long,
    @Json(name = "bathrooms") val bathrooms: Long,
    @Json(name = "address") val address: String,
    @Json(name = "province") val province: String,
    @Json(name = "municipality") val municipality: String,
    @Json(name = "district") val district: String,
    @Json(name = "country") val country: String,
    @Json(name = "neighborhood") val neighborhood: String,
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double,
    @Json(name = "description") val description: String,
    @Json(name = "multimedia") val multimedia: MultimediaDto,
    @Json(name = "features") val features: FeaturesDto,
)

@JsonClass(generateAdapter = true)
data class PriceInfoDto(
    @Json(name = "price") val price: PriceDto,
)

@JsonClass(generateAdapter = true)
data class PriceDto(
    @Json(name = "amount") val amount: Double,
    @Json(name = "currencySuffix") val currencySuffix: String,
)

@JsonClass(generateAdapter = true)
data class MultimediaDto(
    @Json(name = "images") val images: List<ImageDto>,
)

@JsonClass(generateAdapter = true)
data class ImageDto(
    @Json(name = "url") val url: String,
    @Json(name = "tag") val tag: String,
)

@JsonClass(generateAdapter = true)
data class FeaturesDto(
    @Json(name = "hasAirConditioning") val hasAirConditioning: Boolean,
    @Json(name = "hasBoxRoom") val hasBoxRoom: Boolean,
)

fun PropertyListItemDto.toDomain() = PropertyListItem(
    propertyCode = propertyCode,
    thumbnail = thumbnail,
    floor = floor,
    price = price,
    priceInfo = priceInfo.toDomain(),
    propertyType = propertyType,
    operation = operation,
    size = size,
    exterior = exterior,
    rooms = rooms,
    bathrooms = bathrooms,
    address = address,
    province = province,
    municipality = municipality,
    district = district,
    country = country,
    neighborhood = neighborhood,
    latitude = latitude,
    longitude = longitude,
    description = description,
    multimedia = multimedia.toDomain(),
    features = features.toDomain()
)

fun PriceInfoDto.toDomain() = PriceInfo(
    price = price.toDomain()
)

fun PriceDto.toDomain() = Price(
    amount = amount,
    currencySuffix = currencySuffix
)

fun MultimediaDto.toDomain() = Multimedia(
    images = images.map { it.toDomain() }
)

fun ImageDto.toDomain() = Image (
    url = url,
    tag = tag
)

fun FeaturesDto.toDomain() = Features (
    hasBoxRoom = hasBoxRoom,
    hasAirConditioning = hasAirConditioning
)