package cz.jvyh.o2.scratch.common.ui.scratch

import androidx.compose.runtime.Immutable
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.platform.scratch.ScratchController
import cz.jvyh.o2.scratch.common.ui.scratch.ScratchViewModel.State
import cz.jvyh.o2.scratch.shared.common.ui.viewmodel.BaseViewModel

internal class ScratchViewModel(
    logger: Logger,
    private val controller: ScratchController,
) : BaseViewModel<State>(logger) {
    override val initialState: State get() = State()

    suspend fun onScratchClicked() = controller.scratch().let { c -> updateState { s -> s.copy(code = c) } }

    @Immutable
    data class State(
        val code: String? = null,
    )
}