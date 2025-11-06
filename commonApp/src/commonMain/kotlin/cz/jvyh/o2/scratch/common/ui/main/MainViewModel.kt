package cz.jvyh.o2.scratch.common.ui.main

import androidx.compose.runtime.Immutable
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.ui.main.MainViewModel.State
import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.ui.viewmodel.BaseViewModel

class MainViewModel(
    logger: Logger,
) : BaseViewModel<State>(logger) {
    override val initialState: State get() = State()

    @Immutable
    data class State(
        val stateLabel: StringKey? = null,
    )
}