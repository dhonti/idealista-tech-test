package com.dhontiveros.idealistatechtest.presentation.features.list

import androidx.lifecycle.viewModelScope
import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.core.di.CalendarProvider
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIErrorEffect
import com.dhontiveros.idealistatechtest.presentation.base.BaseViewModel
import com.dhontiveros.idealistatechtest.presentation.usecases.GetAllProperties
import com.dhontiveros.idealistatechtest.presentation.usecases.GetRemotePropertyById
import com.dhontiveros.idealistatechtest.presentation.usecases.RemoveFavProperty
import com.dhontiveros.idealistatechtest.presentation.usecases.SaveFavProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAllProperties: GetAllProperties,
    private val saveFavProperty: SaveFavProperty,
    private val removeFavProperty: RemoveFavProperty,
    private val getRemotePropertyById: GetRemotePropertyById,
    private val calendarProvider: CalendarProvider
) : BaseViewModel<ListContract.State, ListContract.Effect>() {

    override fun createInitialState(): ListContract.State =
        ListContract.State(ListContract.ListsState.Idle)

    fun getUsersList() {
        viewModelScope.launch {
            getAllProperties()
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Loading) }
                        }

                        is Resource.Empty -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Idle) }
                        }

                        is Resource.Success -> {
                            setState {
                                copy(
                                    listPropertiesState = ListContract.ListsState.Done,
                                    currentList = it.data
                                )
                            }
                        }

                        is Resource.Error -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Done) }
                            setErrorEffect(BaseUIErrorEffect(appException = it.exception))
                        }
                    }
                }
        }
    }

    fun updateStateWithNewList(currentList: List<PropertyListItem>) {
        setState {
            copy(
                listPropertiesState = ListContract.ListsState.Done,
                currentList = currentList
            )
        }
    }

    fun getPropertyById(id: Int) {
        viewModelScope.launch {
            getRemotePropertyById(id)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Loading) }
                        }

                        is Resource.Empty -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Idle) }
                        }

                        is Resource.Success -> {
                            setState {
                                copy(
                                    listPropertiesState = ListContract.ListsState.Done,
                                    selectedItem = it.data
                                )
                            }
                            setEffect { ListContract.Effect.GoDetail(item = it.data) }
                        }

                        is Resource.Error -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Done) }
                            setErrorEffect(BaseUIErrorEffect(appException = it.exception))
                        }
                    }
                }
        }
    }

    fun updateFavProperty(item: PropertyListItem, indexPos: Int) {
        if (item.isFavorite) {
            removeFav(item, indexPos)
        } else {
            saveFav(item, indexPos)
        }

    }

    private fun saveFav(item: PropertyListItem, indexPos: Int) {
        viewModelScope.launch {
            val dateSaveFav = calendarProvider.getCalendarInstanceMillis()
            item.dateFav = dateSaveFav
            saveFavProperty(item)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Loading) }
                        }

                        is Resource.Empty -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Idle) }
                        }

                        is Resource.Success -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Done) }
                            setEffect {
                                ListContract.Effect.UpdateFav(
                                    indexPos = indexPos,
                                    isFav = true,
                                    dateSaveFav = dateSaveFav
                                )
                            }
                        }

                        is Resource.Error -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Done) }
                            setErrorEffect(BaseUIErrorEffect(appException = it.exception))
                        }
                    }
                }
        }
    }

    private fun removeFav(item: PropertyListItem, indexPos: Int) {
        viewModelScope.launch {
            removeFavProperty(item)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Loading) }
                        }

                        is Resource.Empty -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Idle) }
                        }

                        is Resource.Success -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Done) }
                            setEffect {
                                ListContract.Effect.UpdateFav(
                                    indexPos = indexPos,
                                    isFav = false,
                                    dateSaveFav = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            setState { copy(listPropertiesState = ListContract.ListsState.Done) }
                            setErrorEffect(BaseUIErrorEffect(appException = it.exception))
                        }
                    }
                }
        }
    }

}
