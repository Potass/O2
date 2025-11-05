package cz.jvyh.o2.scratch.common.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.platform.AppContentController
import cz.jvyh.o2.scratch.common.ui.AppContentViewModel.State
import cz.jvyh.o2.scratch.shared.common.domain.AppDestinationToNavigate
import cz.jvyh.o2.scratch.shared.common.domain.DialogId
import cz.jvyh.o2.scratch.shared.common.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class AppContentViewModel(
    logger: Logger,
    private val controller: AppContentController,
) : BaseViewModel<State>(logger) {
    override val initialState: State get() = State()

    init {
        viewModelScope.launch {
            controller.busyIndicatorVisibilityFlow.collect {
                updateState { s -> s.copy(isBusyIndicatorVisible = it) }
            }
        }

        viewModelScope.launch {
            controller.dialogToShowFlow.collect {
                updateState { s -> s.copy(dialogToShowId = it) }
            }
        }

        viewModelScope.launch {
            controller.destinationToNavigateFlow.collect {
                updateState { s -> s.copy(destinationToNavigate = it) }
            }
        }
    }

    fun invalidateDialogToShowId() = updateState { it.invalidateDialogToShowId() }

    fun invalidateDestinationToNavigate() = updateState { it.invalidateDestinationToNavigate() }

    @Immutable
    data class State(
        val isBusyIndicatorVisible: Boolean = false,
        val dialogToShowId: DialogId? = null,
        val destinationToNavigate: AppDestinationToNavigate? = null,
    ) {
        fun invalidateDialogToShowId() = copy(dialogToShowId = null)
        fun invalidateDestinationToNavigate() = copy(destinationToNavigate = null)
    }
}