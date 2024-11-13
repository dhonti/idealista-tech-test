package com.dhontiveros.idealistatechtest.data.repository

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.data.local.datasource.PropertyLocalDs
import com.dhontiveros.idealistatechtest.data.remote.datasource.PropertyRemoteDs
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.getMockPropertyDetailItem
import com.dhontiveros.idealistatechtest.domain.getMockPropertyListItem
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class PropertyRepositoryTest {

    private val remoteDataSource: PropertyRemoteDs = mock()

    private val localDataSource: PropertyLocalDs = mock()

    private lateinit var propertyRepository: PropertyRepository

    @Before
    fun setUp() {
        propertyRepository = PropertyRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `getAllProperties() should emit Resource_Loading and then remote list if is successful`() = runTest {
        // Arrange
        val remoteList = listOf(getMockPropertyListItem(propertyCode = "1", isFavorite = false))
        `when`(remoteDataSource.getRemoteList()).thenReturn(Resource.Success(remoteList))

        // Act
        val result = propertyRepository.getAllProperties().toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(remoteList, (result[1] as Resource.Success).data)
    }

    @Test
    fun `getRemoteDetail should emit Resource_Loading and then remote detail if is successful`() = runTest {
        // Arrange
        val propertyDetail = getMockPropertyDetailItem(adid = 1L)
        `when`(remoteDataSource.getRemoteDetail(1)).thenReturn(Resource.Success(propertyDetail))

        // Act
        val result = propertyRepository.getRemoteDetail(1).toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(propertyDetail, (result[1] as Resource.Success).data)
    }

    @Test
    fun `getLocalFavProperties should emit Resource_Loading and then local list if is successful`() = runTest {
        // Arrange
        val localList = listOf(getMockPropertyListItem(propertyCode = "1", isFavorite = true))
        `when`(localDataSource.getAll()).thenReturn(Resource.Success(localList))

        // Act
        val result = propertyRepository.getLocalFavProperties().toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(localList, (result[1] as Resource.Success).data)
    }

    @Test
    fun `insertLocalFavProperty should emit Resource_Loading and then insert result`() = runTest {
        // Arrange
        val propertyItem = getMockPropertyListItem(propertyCode = "1", isFavorite = true)
        `when`(localDataSource.insert(any())).thenReturn(Resource.Success(true))

        // Act
        val result = propertyRepository.insertLocalFavProperty(propertyItem).toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertTrue((result[1] as Resource.Success).data)
    }

    @Test
    fun `removeLocalFavProperty should emit Resource_Loading and then remove result if is successful`() = runTest {
        // Arrange
        val propertyItem = getMockPropertyListItem(propertyCode = "1", isFavorite = true)
        `when`(localDataSource.remove(any())).thenReturn(Resource.Success(true))

        // Act
        val result = propertyRepository.removeLocalFavProperty(propertyItem).toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertTrue((result[1] as Resource.Success).data)
    }

    @Test
    fun `insertLocalFavProperty should emit Resource_Error when insert fails`() = runTest {
        // Arrange
        val propertyItem = getMockPropertyListItem(propertyCode = "1", isFavorite = true)
        val localDbException = AppException.LocalDBException
        `when`(localDataSource.insert(any())).thenReturn(Resource.Error(exception = localDbException))

        // Act
        val result = propertyRepository.insertLocalFavProperty(propertyItem).toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals(localDbException, (result[1] as Resource.Error).exception)
    }

    @Test
    fun `removeLocalFavProperty should emit Resource_Error when remove fails`() = runTest {
        // Arrange
        val propertyItem = getMockPropertyListItem(propertyCode = "1", isFavorite = true)
        val expectedException = AppException.LocalDBException
        `when`(localDataSource.remove(any())).thenReturn(Resource.Error(exception = expectedException))

        // Act
        val result = propertyRepository.removeLocalFavProperty(propertyItem).toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals(expectedException, (result[1] as Resource.Error).exception)
    }
}