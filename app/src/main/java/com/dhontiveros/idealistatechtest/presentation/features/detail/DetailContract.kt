package com.dhontiveros.idealistatechtest.presentation.features.detail

import com.axpec.mvvmcleankotlinflow.presentation.base.BaseUIState
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail

class DetailContract {

    sealed class DetailState {
        data object Idle : DetailState()
        data object Loading : DetailState()
        data object Done : DetailState()
    }

    // -----------------------------------------------------------------------------------

    data class State(
        val detailState: DetailState,
        val currentItem: PropertyDetail? = null
    ) : BaseUIState

}