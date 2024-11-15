package com.dhontiveros.idealistatechtest.core.di

import javax.inject.Inject

open class TestCalendarProvider @Inject constructor() : CalendarProvider {

    override fun getCalendarInstanceMillis(): Long = 123456789L

}