package com.dhontiveros.idealistatechtest.presentation.listusers

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.core.di.CalendarProvider
import com.dhontiveros.idealistatechtest.core.utils.BaseTest
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.getMockPropertyDetailItem
import com.dhontiveros.idealistatechtest.domain.getMockPropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIErrorEffect
import com.dhontiveros.idealistatechtest.presentation.features.list.ListContract
import com.dhontiveros.idealistatechtest.presentation.features.list.ListViewModel
import com.dhontiveros.idealistatechtest.presentation.usecases.GetAllProperties
import com.dhontiveros.idealistatechtest.presentation.usecases.GetRemotePropertyById
import com.dhontiveros.idealistatechtest.presentation.usecases.RemoveFavProperty
import com.dhontiveros.idealistatechtest.presentation.usecases.SaveFavProperty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Calendar

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest : BaseTest() {

    private lateinit var viewModel: ListViewModel

    private val getAllProperties: GetAllProperties = mock()
    private val saveFavProperties: SaveFavProperty = mock()
    private val removeFavProperty: RemoveFavProperty = mock()
    private val getRemotePropertyById: GetRemotePropertyById = mock()
    private val dateProvider: CalendarProvider = mock()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ListViewModel(
            getAllProperties,
            saveFavProperties,
            removeFavProperty,
            getRemotePropertyById,
            dateProvider
        )
    }

    @Test
    fun `getUsersList should update state with list on success`() = runTest {
        // Arrange
        val propertyList = listOf(getMockPropertyListItem())
        whenever(getAllProperties()).thenReturn(flowOf(Resource.Success(propertyList)))

        // Act
        viewModel.getUsersList()

        // Assert
        assertEquals(ListContract.ListsState.Idle, viewModel.uiState.value.listPropertiesState)
        advanceUntilIdle()
        assertEquals(ListContract.ListsState.Done, viewModel.uiState.value.listPropertiesState)
        assertEquals(propertyList, viewModel.uiState.value.currentList)
    }

    @Test
    fun `when getUsersList returns empty, state should be idle`() = runTest {
        // Arrange
        whenever(getAllProperties()).thenReturn(flowOf(Resource.Empty))

        // Act
        viewModel.getUsersList()

        // Assert
        advanceUntilIdle()
        assertEquals(ListContract.ListsState.Idle, viewModel.uiState.value.listPropertiesState)
    }

    @Test
    fun `when getUsersList() returns error, state should handle error`() = runTest {
        // Arrange
        val exception = AppException.GeneralException
        whenever(getAllProperties()).thenReturn(flowOf(Resource.Error(exception)))

        // Act
        viewModel.getUsersList()

        // Assert
        advanceUntilIdle()
        assertEquals(ListContract.ListsState.Done, viewModel.uiState.value.listPropertiesState)
        assertEquals(BaseUIErrorEffect(appException = exception), viewModel.errorEffect.first())
    }

    @Test
    fun `when getPropertyById() is called, state should be success and effect GoDetail`() =
        runTest {
            // Arrange
            val data = getMockPropertyDetailItem(adid = 1L)
            whenever(getRemotePropertyById(1)).thenReturn(
                flowOf(
                    Resource.Loading,
                    Resource.Success(data = data)
                )
            )

            // Act
            viewModel.getPropertyById(1)

            // Assert
            advanceUntilIdle()
            assertEquals(ListContract.ListsState.Done, viewModel.uiState.value.listPropertiesState)
            assertEquals(data, viewModel.uiState.value.selectedItem)
            assertEquals(ListContract.Effect.GoDetail(item = data), viewModel.effect.first())
        }

    @Test
    fun `when updateFavProperty() is called for a favorite item, should call removeFav`() =
        runTest {
            // Arrange
            val property = getMockPropertyListItem(isFavorite = true)
            whenever(removeFavProperty(property)).thenReturn(
                flowOf(
                    Resource.Loading,
                    Resource.Success(data = true)
                )
            )

            // Act
            viewModel.updateFavProperty(property, indexPos = 0)

            // Assert
            advanceUntilIdle()
            assertEquals(ListContract.ListsState.Done, viewModel.uiState.value.listPropertiesState)
            assertEquals(
                ListContract.Effect.UpdateFav(indexPos = 0, isFav = false),
                viewModel.effect.first()
            )
        }

    @Test
    fun `when updateFavProperty() is called for a non-favorite item, should call saveFav`() =
        runTest {
            // Arrange
            val saveFavDate = dateProvider.getCalendarInstanceMillis()
            val property = getMockPropertyListItem(isFavorite = false, dateSaveFav = saveFavDate)
            whenever(saveFavProperties(property)).thenReturn(
                flowOf(
                    Resource.Loading,
                    Resource.Success(data = true)
                )
            )

            // Act
            viewModel.updateFavProperty(property, indexPos = 0)

            // Assert
            advanceUntilIdle()
            assertEquals(ListContract.ListsState.Done, viewModel.uiState.value.listPropertiesState)
            assertEquals(
                ListContract.Effect.UpdateFav(
                    indexPos = 0,
                    isFav = true,
                    dateSaveFav = saveFavDate
                ), viewModel.effect.first()
            )
        }

}