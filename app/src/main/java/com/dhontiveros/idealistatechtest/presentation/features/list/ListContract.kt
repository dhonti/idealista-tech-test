package com.dhontiveros.idealistatechtest.presentation.features.list

import com.axpec.mvvmcleankotlinflow.presentation.base.BaseUIState
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIEffect


class ListContract {

    sealed class ListsState {
        object Idle : ListsState()
        object Loading : ListsState()
        object Done : ListsState()
    }

    data class State(
        val listPropertiesState: ListsState,
        val currentList: List<PropertyListItem>? = null,
        val selectedItem: PropertyDetail? = null
    ) : BaseUIState

    sealed class Effect : BaseUIEffect {
        data class GoDetail(val item: PropertyDetail) : Effect()
        data class UpdateFav(val indexPos: Int, val isFav: Boolean, val dateSaveFav: Long?) : Effect()
    }

}