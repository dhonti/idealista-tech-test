package com.dhontiveros.idealistatechtest.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dhontiveros.idealistatechtest.data.local.entity.PropertyLocalModel
import com.dhontiveros.idealistatechtest.data.local.utils.DATE_FAV_COLUMN
import com.dhontiveros.idealistatechtest.data.local.utils.PROPERTY_TABLE

@Dao
interface PropertyDao {

    @Query("SELECT * FROM $PROPERTY_TABLE ORDER BY $DATE_FAV_COLUMN DESC")
    fun getAll(): List<PropertyLocalModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: PropertyLocalModel)

    @Delete
    fun remove(user: PropertyLocalModel)
}