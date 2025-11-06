package cz.jvyh.o2.scratch.common.ui.scratch

import androidx.compose.runtime.Immutable
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.ui.scratch.ScratchViewModel.State
import cz.jvyh.o2.scratch.shared.common.ui.viewmodel.BaseViewModel

class ScratchViewModel(
    logger: Logger,
) : BaseViewModel<State>(logger) {
    override val initialState: State get() = State()

    @Immutable
    data class State(
        val code: String? = null,
    )
}