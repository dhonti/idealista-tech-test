package com.dhontiveros.idealistatechtest.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axpec.mvvmcleankotlinflow.presentation.base.BaseUIState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : BaseUIState, Effect : BaseUIEffect> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    private val _errorEffect: Channel<BaseUIErrorEffect> = Channel()
    val errorEffect = _errorEffect.receiveAsFlow()

    val currentState: State
        get() = uiState.value

    fun setState(reduce: State.() -> State) {
        _uiState.value = currentState.reduce()
    }

    fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    fun setErrorEffect(errorEffectValue: BaseUIErrorEffect) {
        viewModelScope.launch { _errorEffect.send(errorEffectValue) }
    }

}