package com.dhontiveros.idealistatechtest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dhontiveros.idealistatechtest.data.local.dao.PropertyDao
import com.dhontiveros.idealistatechtest.data.local.entity.PropertyLocalModel

@Database(
    entities = [PropertyLocalModel::class],
    version = 1
)
abstract class PropertyDB : RoomDatabase(){
    abstract fun propertyDao(): PropertyDao
}