package com.dhontiveros.idealistatechtest.domain.usecases

import com.dhontiveros.idealistatechtest.core.utils.BaseTest
import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.getMockPropertyListItem
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import com.dhontiveros.idealistatechtest.presentation.usecases.GetAllProperties
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
class GetAllPropertiesTest : BaseTest() {

    private val repository: PropertyRepository = mock()

    private lateinit var getAllProperties: GetAllProperties

    @Before
    fun setUp() {
        getAllProperties = GetAllPropertiesImpl(coroutineTestRule.testDispatcher, repository)
    }

    @Test
    fun `buildRequest should emit Resource_Success when both remote and local data are successful`() =
        runTest {
            // Arrange
            val remoteProperties = listOf(
                getMockPropertyListItem(propertyCode = "1", isFavorite = false),
                getMockPropertyListItem(propertyCode = "2", isFavorite = false)
            )
            val localFavorites = listOf(
                getMockPropertyListItem(propertyCode = "1", isFavorite = true)
            )

            `when`(repository.getAllProperties()).thenReturn(
                flowOf(
                    Resource.Success(
                        remoteProperties
                    )
                )
            )
            `when`(repository.getLocalFavProperties()).thenReturn(
                flowOf(
                    Resource.Success(
                        localFavorites
                    )
                )
            )

            // Act
            val result = getAllProperties.buildRequest(null).toList()

            // Assert
            assertEquals(1, result.size)
            assertTrue(result.first() is Resource.Success)
            val data = (result.first() as Resource.Success).data
            assertTrue(data.find { it.propertyCode == "1" }?.isFavorite == true)
            assertTrue(data.find { it.propertyCode == "2" }?.isFavorite == false)
        }

    @Test
    fun `buildRequest should emit Resource_Error if remote fails`() = runTest {
        // Arrange
        val serverException = AppException.ServerErrorException
        `when`(repository.getAllProperties()).thenReturn(flowOf(Resource.Error(exception = AppException.ServerErrorException)))

        // Act
        val result = getAllProperties.buildRequest(null).toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result.first() is Resource.Error)
        assertEquals(serverException, (result.first() as Resource.Error).exception)
    }

    @Test
    fun `buildRequest should emit local Resource_Error if remote succeeds but local fails`() =
        runTest {
            // Arrange
            val remoteProperties = listOf(
                getMockPropertyListItem(propertyCode = "1", isFavorite = false),
                getMockPropertyListItem(propertyCode = "2", isFavorite = false)
            )
            val localDbException = AppException.LocalDBException

            `when`(repository.getAllProperties()).thenReturn(flowOf(Resource.Success(data = remoteProperties)))
            `when`(repository.getLocalFavProperties()).thenReturn(flowOf(Resource.Error(exception = localDbException)))

            // Act
            val result = getAllProperties.buildRequest(null).toList()

            // Assert
            assertEquals(1, result.size)
            assertTrue(result.first() is Resource.Error)
            assertEquals(localDbException, (result.first() as Resource.Error).exception)
        }
}