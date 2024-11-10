package com.dhontiveros.idealistatechtest.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhontiveros.idealistatechtest.data.local.utils.PROPERTY_TABLE
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem

@Entity(tableName = PROPERTY_TABLE)
data class PropertyLocalModel(
    @PrimaryKey
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "district")
    val district: String,
    @ColumnInfo(name = "municipality")
    val municipality: String,
    @ColumnInfo(name = "province")
    val province: String,
    @ColumnInfo(name = "rooms")
    val rooms: Long,
    @ColumnInfo(name = "bathrooms")
    val bathrooms: Long,
    @ColumnInfo(name = "size")
    val size: Double,
)


fun PropertyLocalModel.toDomain() = PropertyListItem(
    propertyCode = code,
    thumbnail = thumbnail,
    propertyType = type,
    address = address,
    district = district,
    municipality = municipality,
    province = province,
    rooms = rooms,
    bathrooms = bathrooms,
    size = size
)

