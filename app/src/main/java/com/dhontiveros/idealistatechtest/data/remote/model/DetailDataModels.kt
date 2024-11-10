package com.dhontiveros.idealistatechtest.data.remote.model

import com.dhontiveros.idealistatechtest.domain.models.Emissions
import com.dhontiveros.idealistatechtest.domain.models.EnergyCertification
import com.dhontiveros.idealistatechtest.domain.models.EnergyConsumption
import com.dhontiveros.idealistatechtest.domain.models.ImageDetail
import com.dhontiveros.idealistatechtest.domain.models.MoreCharacteristics
import com.dhontiveros.idealistatechtest.domain.models.MultimediaDetail
import com.dhontiveros.idealistatechtest.domain.models.PriceInfoDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.Ubication
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PropertyDetailDto(
    @Json(name = "adid") var adid: Long? = null,
    @Json(name = "price") var price: Double? = null,
    @Json(name = "priceInfo") var priceInfoDetail: PriceInfoDetailDto? = PriceInfoDetailDto(),
    @Json(name = "operation") var operation: String? = null,
    @Json(name = "propertyType") var propertyType: String? = null,
    @Json(name = "extendedPropertyType") var extendedPropertyType: String? = null,
    @Json(name = "homeType") var homeType: String? = null,
    @Json(name = "state") var state: String? = null,
    @Json(name = "multimedia") var multimedia: MultimediaDetailDto? = MultimediaDetailDto(),
    @Json(name = "propertyComment") var propertyComment: String? = null,
    @Json(name = "ubication") var ubication: UbicationDto? = UbicationDto(),
    @Json(name = "country") var country: String? = null,
    @Json(name = "moreCharacteristics") var moreCharacteristics: MoreCharacteristicsDto? = MoreCharacteristicsDto(),
    @Json(name = "energyCertification") var energyCertification: EnergyCertificationDto? = EnergyCertificationDto()
)

@JsonClass(generateAdapter = true)
data class PriceInfoDetailDto(
    @Json(name = "amount") var amount: Double? = null,
    @Json(name = "currencySuffix") var currencySuffix: String? = null
)

@JsonClass(generateAdapter = true)
data class MultimediaDetailDto(
    @Json(name = "images") var images: List<ImagesDetailDto> = emptyList()
)

@JsonClass(generateAdapter = true)
data class ImagesDetailDto(
    @Json(name = "url") var url: String? = null,
    @Json(name = "tag") var tag: String? = null,
    @Json(name = "localizedName") var localizedName: String? = null,
    @Json(name = "multimediaId") var multimediaId: Long? = null
)

@JsonClass(generateAdapter = true)
data class UbicationDto(
    @Json(name = "latitude") var latitude: Double? = null,
    @Json(name = "longitude") var longitude: Double? = null
)

@JsonClass(generateAdapter = true)
data class MoreCharacteristicsDto(
    @Json(name = "communityCosts") var communityCosts: Double? = null,
    @Json(name = "roomNumber") var roomNumber: Int? = null,
    @Json(name = "bathNumber") var bathNumber: Int? = null,
    @Json(name = "exterior") var exterior: Boolean? = null,
    @Json(name = "housingFurnitures") var housingFurnitures: String? = null,
    @Json(name = "agencyIsABank") var agencyIsABank: Boolean? = null,
    @Json(name = "energyCertificationType") var energyCertificationType: String? = null,
    @Json(name = "flatLocation") var flatLocation: String? = null,
    @Json(name = "modificationDate") var modificationDate: Long? = null,
    @Json(name = "constructedArea") var constructedArea: Long? = null,
    @Json(name = "lift") var lift: Boolean? = null,
    @Json(name = "boxroom") var boxroom: Boolean? = null,
    @Json(name = "isDuplex") var isDuplex: Boolean? = null,
    @Json(name = "floor") var floor: String? = null,
    @Json(name = "status") var status: String? = null
)

@JsonClass(generateAdapter = true)
data class EnergyConsumptionDto(
    @Json(name = "type") var type: String? = null
)

@JsonClass(generateAdapter = true)
data class EnergyCertificationDto(
    @Json(name = "title") var title: String? = null,
    @Json(name = "energyConsumption") var energyConsumption: EnergyConsumptionDto? = EnergyConsumptionDto(),
    @Json(name = "emissions") var emissions: EmissionsDto? = EmissionsDto()
)

@JsonClass(generateAdapter = true)
data class EmissionsDto(
    @Json(name = "type") var type: String? = null
)

fun PropertyDetailDto.toDomain() = PropertyDetail(
    adid = adid!!,
    price = price!!,
    priceInfoDetail = priceInfoDetail!!.toDomain(),
    operation = operation!!,
    propertyType = propertyType!!,
    extendedPropertyType = extendedPropertyType!!,
    homeType = homeType!!,
    state = state!!,
    multimedia = multimedia!!.toDomain(),
    propertyComment = propertyComment!!,
    ubication = ubication!!.toDomain(),
    country = country!!,
    moreCharacteristics = moreCharacteristics!!.toDomain(),
    energyCertification = energyCertification!!.toDomain()
)

fun PriceInfoDetailDto.toDomain() = PriceInfoDetail(
    amount = amount!!,
    currencySuffix = currencySuffix!!
)

fun MultimediaDetailDto.toDomain() = MultimediaDetail(
    images = images.map { it.toDomain() }
)

fun ImagesDetailDto.toDomain() = ImageDetail(
    url = url!!,
    tag = tag!!,
    localizedName = localizedName!!,
    multimediaID = multimediaId!!
)

fun UbicationDto.toDomain() = Ubication(
    latitude = latitude!!,
    longitude = longitude!!
)

fun MoreCharacteristicsDto.toDomain() = MoreCharacteristics(
    communityCosts = communityCosts!!,
    roomNumber = roomNumber!!,
    bathNumber = bathNumber!!,
    exterior = exterior!!,
    housingFurnitures = housingFurnitures!!,
    agencyIsABank = agencyIsABank!!,
    energyCertificationType = energyCertificationType!!,
    flatLocation = flatLocation!!,
    modificationDate = modificationDate!!,
    constructedArea = constructedArea!!,
    lift = lift!!,
    boxroom = boxroom!!,
    isDuplex = isDuplex!!,
    floor = floor!!,
    status = status!!
)

fun EnergyCertificationDto.toDomain() = EnergyCertification(
    title = title!!,
    energyConsumption = energyConsumption!!.toDomain(),
    emissions = emissions!!.toDomain()
)

fun EnergyConsumptionDto.toDomain() = EnergyConsumption(
    type = type!!
)

fun EmissionsDto.toDomain() = Emissions(
    type = type!!
)