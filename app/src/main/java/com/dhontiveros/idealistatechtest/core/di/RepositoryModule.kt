package com.dhontiveros.idealistatechtest.core.di

import com.dhontiveros.idealistatechtest.data.repository.PropertyRepositoryImpl
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideRepository(impl: PropertyRepositoryImpl): PropertyRepository

}