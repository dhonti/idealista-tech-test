package com.dhontiveros.idealistatechtest.domain.exceptions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.axpec.mvvmcleankotlinflow.presentation.base.BaseUIState
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIEffect
import com.dhontiveros.idealistatechtest.presentation.base.BaseUIErrorEffect
import com.dhontiveros.idealistatechtest.presentation.base.BaseViewModel
import kotlinx.coroutines.launch


fun LifecycleOwner.launchInScope(actionScope: suspend () -> Unit) {
    this.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            actionScope()
        }
    }
}


inline fun <reified State : BaseUIState> LifecycleOwner.observeState(
    viewModel: BaseViewModel<*, *>,
    crossinline handleMethod: (state: State) -> Unit
) {
    launchInScope {
        viewModel.uiState.collect {
            if (it is State) {
                handleMethod(it)
            }
        }
    }
}

inline fun <reified Effect : BaseUIEffect> LifecycleOwner.observeEffect(
    viewModel: BaseViewModel<*, *>,
    crossinline handleMethod: (effect: Effect) -> Unit
) {
    launchInScope {
        viewModel.effect.collect {
            if (it is Effect) {
                handleMethod(it)
            }
        }
    }
}

fun LifecycleOwner.observeErrorEffect(
    viewModel: BaseViewModel<*, *>,
    handleMethod: (effect: BaseUIErrorEffect) -> Unit
) {
    launchInScope {
        viewModel.errorEffect.collect {
            handleMethod(it)
        }
    }
}