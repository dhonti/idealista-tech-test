package com.dhontiveros.idealistatechtest.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CalendarProviderModule::class]
)
object TestCalendarProviderModule {
    @Provides
    fun provideCalendarProvider(): CalendarProvider = TestCalendarProvider()
}