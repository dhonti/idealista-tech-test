package com.dhontiveros.idealistatechtest.domain.usecases

import com.dhontiveros.idealistatechtest.core.utils.BaseTest
import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.getMockPropertyDetailItem
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetRemoteByIdTest : BaseTest() {

    private val repository: PropertyRepository = mock()

    private lateinit var getRemotePropertyByIdImpl: GetRemotePropertyByIdImpl

    @Before
    fun setUp() {
        getRemotePropertyByIdImpl = GetRemotePropertyByIdImpl(coroutineTestRule.testDispatcher, repository)
    }

    @Test
    fun `buildRequest should emit Resource_Error when params is null`() = runTest {
        // Act
        val result = getRemotePropertyByIdImpl.buildRequest(null).toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result.first() is Resource.Error)
        assertEquals(AppException.BadRequestException, (result.first() as Resource.Error).exception)
    }

    @Test
    fun `buildRequest should emit Resource_Success when repository returns data successfully`() = runTest {
        // Arrange
        val propertyDetail = getMockPropertyDetailItem(adid = 1L)
        `when`(repository.getRemoteDetail(id = 1)).thenReturn(flowOf(Resource.Success(propertyDetail)))

        // Act
        val result = getRemotePropertyByIdImpl.buildRequest(1).toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result.first() is Resource.Success)
        assertEquals(propertyDetail, (result.first() as Resource.Success).data)
    }

    @Test
    fun `buildRequest should emit Resource_Error when repository returns error`() = runTest {
        // Arrange
        val exception = AppException.RequestException
        `when`(repository.getRemoteDetail(1)).thenReturn(flowOf(Resource.Error(exception)))

        // Act
        val result = getRemotePropertyByIdImpl.buildRequest(1).toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result.first() is Resource.Error)
        assertEquals(exception, (result.first() as Resource.Error).exception)
    }
}
