package cz.jvyh.o2.scratch.common.ui.activation

import androidx.compose.runtime.Immutable
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.ui.activation.ActivationViewModel.State
import cz.jvyh.o2.scratch.shared.common.ui.viewmodel.BaseViewModel

class ActivationViewModel(
    logger: Logger,
) : BaseViewModel<State>(logger) {
    override val initialState: State get() = State()

    @Immutable
    data class State(
        val todo: Any? = null,
    )
}