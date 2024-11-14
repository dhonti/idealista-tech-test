package com.dhontiveros.idealistatechtest.presentation.features.favorites

import com.axpec.mvvmcleankotlinflow.presentation.base.BaseUIState
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIEffect

class FavoritesContract {

    sealed class FavoritesState {
        object Idle : FavoritesState()
        object Loading : FavoritesState()
        object Done : FavoritesState()
    }

    data class State(
        val listFavState: FavoritesState,
        val currentList: List<PropertyListItem>? = null,
        val selectedItem: PropertyDetail? = null
    ) : BaseUIState

    sealed class Effect : BaseUIEffect {
        data class GoDetail(val item: PropertyDetail) : Effect()
        data class UpdateFav(val indexPos: Int) : Effect()
    }

}