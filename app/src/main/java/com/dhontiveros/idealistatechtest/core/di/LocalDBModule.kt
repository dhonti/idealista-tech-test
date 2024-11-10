package com.dhontiveros.idealistatechtest.core.di

import android.content.Context
import androidx.room.Room
import com.dhontiveros.idealistatechtest.data.local.dao.PropertyDao
import com.dhontiveros.idealistatechtest.data.local.database.PropertyDB
import com.dhontiveros.idealistatechtest.data.local.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDBModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        PropertyDB::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    fun provideUserDao(database: PropertyDB): PropertyDao = database.propertyDao()

}