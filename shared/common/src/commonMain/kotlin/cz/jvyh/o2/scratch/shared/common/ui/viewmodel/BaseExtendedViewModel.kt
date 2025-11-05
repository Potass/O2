package cz.jvyh.o2.scratch.shared.common.ui.viewmodel

import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.shared.common.infrastructure.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

abstract class BaseExtendedViewModel<State : Any>(
    logger: Logger,
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<State>(logger) {
    private val backgroundDispatcher: CoroutineDispatcher = dispatcherProvider.default()

    /**
     * Updates the [MutableStateFlow.value] atomically using the specified [function] of its value applied on background thread.
     *
     * [function] may be evaluated multiple times, if [MutableStateFlow.value] is being concurrently updated.
     */
    private suspend fun MutableStateFlow<State>.updateOnBackground(function: (State) -> State) {
        while (true) {
            val prevValue = value
            val nextValue = withContext(backgroundDispatcher) { function(prevValue) }
            if (compareAndSet(prevValue, nextValue)) return
        }
    }

    protected suspend fun updateStateOnBackground(function: (State) -> State) = _stateFlow.updateOnBackground(function)
}