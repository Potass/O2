package cz.jvyh.o2.scratch.common.ui.scratch

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.platform.scratch.ScratchController
import cz.jvyh.o2.scratch.common.ui.scratch.ScratchViewModel.State
import cz.jvyh.o2.scratch.shared.common.infrastructure.StringDefaults
import cz.jvyh.o2.scratch.shared.common.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

internal class ScratchViewModel(
    logger: Logger,
    private val controller: ScratchController,
) : BaseViewModel<State>(logger) {
    override val initialState: State get() = State()

    init {
        updateState { s -> s.copy(code = controller.code) }
    }

    fun onScratchClicked() {
        viewModelScope.launch {
            controller.scratch().let { c -> updateState { s -> s.copy(code = c) } }
        }
    }

    @Immutable
    data class State(
        val code: String = StringDefaults.DEFAULT_VALUE,
    )
}