package com.dhontiveros.idealistatechtest.core.di

import com.dhontiveros.idealistatechtest.data.local.datasource.PropertyLocalDs
import com.dhontiveros.idealistatechtest.data.local.datasource.PropertyLocalDsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun provideUserLocalDS(impl: PropertyLocalDsImpl): PropertyLocalDs

}