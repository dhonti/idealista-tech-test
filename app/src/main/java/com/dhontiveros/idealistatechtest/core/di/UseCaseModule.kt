package com.dhontiveros.idealistatechtest.core.di

import com.dhontiveros.idealistatechtest.domain.usecases.GetAllPropertiesImpl
import com.dhontiveros.idealistatechtest.domain.usecases.GetFavPropertiesImpl
import com.dhontiveros.idealistatechtest.domain.usecases.GetRemotePropertyByIdImpl
import com.dhontiveros.idealistatechtest.domain.usecases.RemoveFavPropertyImpl
import com.dhontiveros.idealistatechtest.domain.usecases.SaveFavPropertyImpl
import com.dhontiveros.idealistatechtest.presentation.usecases.GetAllProperties
import com.dhontiveros.idealistatechtest.presentation.usecases.GetFavProperties
import com.dhontiveros.idealistatechtest.presentation.usecases.GetRemotePropertyById
import com.dhontiveros.idealistatechtest.presentation.usecases.RemoveFavProperty
import com.dhontiveros.idealistatechtest.presentation.usecases.SaveFavProperty
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun provideGetAllProperties(impl: GetAllPropertiesImpl): GetAllProperties

    @Binds
    @ViewModelScoped
    abstract fun provideGetRemotePropertyById(impl: GetRemotePropertyByIdImpl): GetRemotePropertyById

    @Binds
    @ViewModelScoped
    abstract fun provideGetFavProperties(impl: GetFavPropertiesImpl): GetFavProperties

    @Binds
    @ViewModelScoped
    abstract fun provideRemoveFavProperty(impl: RemoveFavPropertyImpl): RemoveFavProperty

    @Binds
    @ViewModelScoped
    abstract fun provideSaveFavProperty(impl: SaveFavPropertyImpl): SaveFavProperty
}