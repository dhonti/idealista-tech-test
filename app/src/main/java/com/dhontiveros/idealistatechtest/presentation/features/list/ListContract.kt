package com.dhontiveros.idealistatechtest.presentation.features.list

import com.axpec.mvvmcleankotlinflow.presentation.base.BaseUIState
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIEffect


class ListContract {

    sealed class ListsState {
        data object Idle : ListsState()
        data object Loading : ListsState()
        data object Done : ListsState()
    }

    data class State(
        val listPropertiesState: ListsState,
        val currentList: List<PropertyListItem>? = null,
        val selectedItem: PropertyDetail? = null
    ) : BaseUIState

    sealed class Effect : BaseUIEffect {
        data class GoDetail(val item: PropertyDetail) : Effect()
    }

}