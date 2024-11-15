package com.dhontiveros.idealistatechtest.core.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dhontiveros.idealistatechtest.presentation.common.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
abstract class BaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

//
//    @get:Rule
//    val hiltRule = HiltAndroidRule(this)
}