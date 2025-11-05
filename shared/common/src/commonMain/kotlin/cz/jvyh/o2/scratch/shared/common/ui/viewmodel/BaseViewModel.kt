@file:Suppress("PropertyName")

package cz.jvyh.o2.scratch.shared.common.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.shared.common.infrastructure.NEW_LINE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Any>(
    logger: Logger
) : ViewModel() {
    protected abstract val initialState: State
    protected val _stateFlow = MutableStateFlow(initialState)
    protected val lastState: State get() = _stateFlow.value
    val stateFlow: StateFlow<State> = _stateFlow

    init {
        viewModelScope.launch {
            // Just a convenience state logging mechanism.
            stateFlow.collectLatest { logger.d { "hash=${hashCode()}$NEW_LINE$it" } }
        }
    }

    protected inline fun updateState(function: (State) -> State) = _stateFlow.update(function)
}