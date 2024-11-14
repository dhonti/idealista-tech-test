package com.dhontiveros.idealistatechtest.presentation.features.favorites

import androidx.lifecycle.viewModelScope
import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIErrorEffect
import com.dhontiveros.idealistatechtest.presentation.base.BaseViewModel
import com.dhontiveros.idealistatechtest.presentation.usecases.GetFavProperties
import com.dhontiveros.idealistatechtest.presentation.usecases.GetRemotePropertyById
import com.dhontiveros.idealistatechtest.presentation.usecases.RemoveFavProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavProperties: GetFavProperties,
    private val removeFavProperty: RemoveFavProperty,
    private val getRemotePropertyById: GetRemotePropertyById
) : BaseViewModel<FavoritesContract.State, FavoritesContract.Effect>() {

    override fun createInitialState(): FavoritesContract.State =
        FavoritesContract.State(FavoritesContract.FavoritesState.Idle)

    init {
        getFavoritesList()
    }

    fun getFavoritesList() {
        viewModelScope.launch {
            getFavProperties()
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Loading) }
                        }

                        is Resource.Empty -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Idle) }
                        }

                        is Resource.Success -> {
                            setState {
                                copy(
                                    listFavState = FavoritesContract.FavoritesState.Done,
                                    currentList = it.data
                                )
                            }
                        }

                        is Resource.Error -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Done) }
                            setErrorEffect(BaseUIErrorEffect(appException = it.exception))
                        }
                    }
                }
        }
    }

    fun updateStateWithNewList(currentList: List<PropertyListItem>) {
        setState {
            copy(
                listFavState = FavoritesContract.FavoritesState.Done,
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
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Loading) }
                        }

                        is Resource.Empty -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Idle) }
                        }

                        is Resource.Success -> {
                            setState {
                                copy(
                                    listFavState = FavoritesContract.FavoritesState.Done,
                                    selectedItem = it.data
                                )
                            }
                            setEffect { FavoritesContract.Effect.GoDetail(item = it.data) }
                        }

                        is Resource.Error -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Done) }
                            setErrorEffect(BaseUIErrorEffect(appException = it.exception))
                        }
                    }
                }
        }
    }

    fun removeFav(item: PropertyListItem, indexPos: Int) {
        viewModelScope.launch {
            removeFavProperty(item)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Loading) }
                        }

                        is Resource.Empty -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Idle) }
                        }

                        is Resource.Success -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Done) }
                            setEffect { FavoritesContract.Effect.UpdateFav(indexPos = indexPos) }
                        }

                        is Resource.Error -> {
                            setState { copy(listFavState = FavoritesContract.FavoritesState.Done) }
                            setErrorEffect(BaseUIErrorEffect(appException = it.exception))
                        }
                    }
                }
        }
    }
}