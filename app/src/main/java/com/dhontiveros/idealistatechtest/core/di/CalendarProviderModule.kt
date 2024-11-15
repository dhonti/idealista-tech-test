package com.dhontiveros.idealistatechtest.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Calendar
import javax.inject.Inject

interface CalendarProvider {
    fun getCalendarInstanceMillis(): Long
}

open class DefaultCalendarProvider @Inject constructor() : CalendarProvider {
    override fun getCalendarInstanceMillis(): Long = Calendar.getInstance().timeInMillis
}

@Module
@InstallIn(SingletonComponent::class)
class CalendarProviderModule {
    @Provides
    fun provideCalendarProvider(impl: DefaultCalendarProvider): CalendarProvider = impl
}