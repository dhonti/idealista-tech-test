package com.dhontiveros.idealistatechtest.presentation.features.detail

import androidx.lifecycle.viewModelScope
import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.usecases.GetRemotePropertyById
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIErrorEffect
import com.dhontiveros.idealistatechtest.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserById: GetRemotePropertyById
) : BaseViewModel<DetailContract.State, Nothing>() {

    override fun createInitialState(): DetailContract.State =
        DetailContract.State(DetailContract.DetailState.Idle)

    private fun getUser(userId: Int) {
        viewModelScope.launch {
            getUserById.execute(userId)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(detailState = DetailContract.DetailState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(detailState = DetailContract.DetailState.Idle) }
                        }
                        is Resource.Success -> {
                            setState {
                                copy(
                                    detailState = DetailContract.DetailState.Done,
                                    currentItem = it.data
                                )
                            }
                        }
                        is Resource.Error -> {
                            setState { copy(detailState = DetailContract.DetailState.Done) }
                            setErrorEffect(BaseUIErrorEffect(appException = it.exception))
                        }
                    }
                }
        }
    }

}