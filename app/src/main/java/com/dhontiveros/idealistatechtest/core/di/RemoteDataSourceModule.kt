package com.dhontiveros.idealistatechtest.core.di

import com.dhontiveros.idealistatechtest.data.remote.datasource.PropertyRemoteDs
import com.dhontiveros.idealistatechtest.data.remote.datasource.PropertyRemoteDsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun provideRemoteDS(impl: PropertyRemoteDsImpl): PropertyRemoteDs

}