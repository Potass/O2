package cz.jvyh.o2.scratch.common.ui.main

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.platform.main.MainController
import cz.jvyh.o2.scratch.common.ui.main.MainViewModel.State
import cz.jvyh.o2.scratch.common.ui.theming.softGreen
import cz.jvyh.o2.scratch.common.ui.theming.softRed
import cz.jvyh.o2.scratch.common.ui.theming.warmOrange
import cz.jvyh.o2.scratch.shared.common.domain.CommonStringKey
import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

internal class MainViewModel(
    logger: Logger,
    private val controller: MainController,
) : BaseViewModel<State>(logger) {
    override val initialState: State get() = State()

    init {
        viewModelScope.launch {
            controller.isActiveFlow.collect {
                updateState { s ->
                    s.copy(
                        stateLabel = resolveStateLabel(it, controller.code),
                        stateColor = resolveStateColor(it, controller.code),
                        isScratchEnabled = it.not()
                    )
                }
            }
        }
        viewModelScope.launch {
            controller.codeFlow.collect {
                updateState { s ->
                    s.copy(
                        stateLabel = resolveStateLabel(controller.isActive, it),
                        stateColor = resolveStateColor(controller.isActive, it),
                        isActivationEnabled = it.isNotBlank()
                    )
                }
            }
        }
    }

    private fun resolveStateLabel(isActive: Boolean, code: String): CommonStringKey =
        if (isActive) CommonStringKey.CommonLabelActivated
        else if (code.isNotBlank()) CommonStringKey.CommonLabelScratched
        else CommonStringKey.CommonLabelUnscratched

    private fun resolveStateColor(isActive: Boolean, code: String): Color =
        if (isActive) softGreen else if (code.isNotBlank()) warmOrange else softRed

    @Immutable
    data class State(
        val stateLabel: StringKey = CommonStringKey.CommonLabelUnscratched,
        val stateColor: Color = softRed,
        val isScratchEnabled: Boolean = true,
        val isActivationEnabled: Boolean = false,
    )
}