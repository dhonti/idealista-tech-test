package com.dhontiveros.idealistatechtest.presentation.features.list

import androidx.lifecycle.viewModelScope
import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.usecases.RemoveFavPropertyImpl
import com.dhontiveros.idealistatechtest.domain.usecases.SaveFavPropertyImpl
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIErrorEffect
import com.dhontiveros.idealistatechtest.presentation.base.BaseViewModel
import com.dhontiveros.idealistatechtest.presentation.usecases.GetAllProperties
import com.dhontiveros.idealistatechtest.presentation.usecases.GetRemotePropertyById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAllProperties: GetAllProperties,
    private val saveFavProperties: SaveFavPropertyImpl,
    private val removeFavProperty: RemoveFavPropertyImpl,
    private val getRemotePropertyById: GetRemotePropertyById
) : BaseViewModel<ListContract.State, ListContract.Effect>() {

    init {
        getUsersList()
    }

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
            saveFavProperties(item)
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
                                    isFav = true
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
                                    isFav = false
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