package com.dhontiveros.idealistatechtest.data.remote.datasource

import com.dhontiveros.idealistatechtest.core.utils.BaseTest
import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.data.remote.api.ApiService
import com.dhontiveros.idealistatechtest.data.remote.getMockPropertyDetailItemDto
import com.dhontiveros.idealistatechtest.data.remote.getMockPropertyListItemDto
import com.dhontiveros.idealistatechtest.data.remote.model.PropertyDetailDto
import com.dhontiveros.idealistatechtest.data.remote.model.PropertyListItemDto
import com.dhontiveros.idealistatechtest.data.remote.model.toDomain
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class PropertyRemoteDsTest : BaseTest() {

    private val apiService: ApiService = mock()

    private lateinit var propertyRemoteDsImpl: PropertyRemoteDs

    @Before
    fun setUp() {
        propertyRemoteDsImpl = PropertyRemoteDsImpl(coroutineTestRule.testDispatcher, apiService)
    }

    @Test
    fun `getRemoteList should return success when API request is successful`() = runTest {
        // Arrange
        val propertyList = listOf(getMockPropertyListItemDto(propertyCode = "1"))
        val response = Response.success(propertyList)
        `when`(apiService.getPropertiesList()).thenReturn(response)

        // Act
        val result = propertyRemoteDsImpl.getRemoteList()

        // Assert
        assertTrue(result is Resource.Success)
        assertEquals(propertyList.map { it.toDomain() }, (result as Resource.Success).data)
    }

    @Test
    fun `getRemoteList should return error when API request fails with HTTP error`() = runTest {
        // Arrange
        val response =
            Response.error<List<PropertyListItemDto>>(400, "Bad Request".toResponseBody())
        `when`(apiService.getPropertiesList()).thenReturn(response)

        // Act
        val result = propertyRemoteDsImpl.getRemoteList()

        // Assert
        assertTrue(result is Resource.Error)
        assertTrue((result as Resource.Error).exception is AppException.GeneralException)
    }


    @Test
    fun `getRemoteList should return error when API request fails with Exception`() = runTest {
        // Arrange
        `when`(apiService.getPropertiesList()).thenThrow(RuntimeException("Test Exception"))

        // Act
        val result = propertyRemoteDsImpl.getRemoteList()

        // Assert
        assertTrue(result is Resource.Error)
        assertTrue((result as Resource.Error).exception is AppException.GeneralException)
    }

    @Test
    fun `getRemoteDetail should return success when API request is successful`() = runTest {
        // Arrange
        val propertyDetail = getMockPropertyDetailItemDto(adid = 1L)
        val response = Response.success(propertyDetail)
        `when`(apiService.getDetailProperty()).thenReturn(response)

        // Act
        val result = propertyRemoteDsImpl.getRemoteDetail(1)

        // Assert
        assertTrue(result is Resource.Success)
        assertEquals(propertyDetail.toDomain(), (result as Resource.Success).data)
    }

    @Test
    fun `getRemoteDetail should return error when API request fails with HTTP error`() = runTest {
        // Arrange
        val response = Response.error<PropertyDetailDto>(400, "Bad Request".toResponseBody())
        `when`(apiService.getDetailProperty()).thenReturn(response)

        // Act
        val result = propertyRemoteDsImpl.getRemoteDetail(1)

        // Assert
        assertTrue(result is Resource.Error)
        assertTrue((result as Resource.Error).exception is AppException.GeneralException)
    }

    @Test
    fun `getRemoteDetail() should return error when API request fails with Exception`() = runTest {
        // Arrange
        `when`(apiService.getDetailProperty()).thenThrow(RuntimeException("Test Exception"))

        // Act
        val result = propertyRemoteDsImpl.getRemoteDetail(1)

        // Assert
        assertTrue(result is Resource.Error)
        assertTrue((result as Resource.Error).exception is AppException.GeneralException)
    }
}