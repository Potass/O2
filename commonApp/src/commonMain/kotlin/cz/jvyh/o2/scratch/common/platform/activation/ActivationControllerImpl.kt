package cz.jvyh.o2.scratch.common.platform.activation

import cz.jvyh.o2.scratch.common.domain.activation.ActivationRequest
import cz.jvyh.o2.scratch.common.domain.shared.ActivationFailedDialogId
import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider
import cz.jvyh.o2.scratch.common.platform.shared.IsActiveFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.IsActiveValueProvider
import cz.jvyh.o2.scratch.shared.common.infrastructure.BooleanDefaults
import cz.jvyh.o2.scratch.shared.common.infrastructure.DispatcherProvider
import cz.jvyh.o2.scratch.shared.common.infrastructure.orDefault
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import cz.jvyh.o2.scratch.shared.common.platform.DialogToShowUpdater
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

// TODO O2 - unit tests
internal class ActivationControllerImpl(
    dispatcherProvider: DispatcherProvider,
    private val processor: ActivationProcessor,
    private val busyIndicatorController: BusyIndicatorController,
    private val codeValueProvider: CodeValueProvider,
    private val dialogToShowUpdater: DialogToShowUpdater,
) : ActivationController, CoroutineScope, IsActiveFlowProvider, IsActiveValueProvider {
    private val _isActiveFlow = MutableStateFlow(BooleanDefaults.DEFAULT_VALUE)
    override val isActiveFlow: Flow<Boolean> = _isActiveFlow
    override val isActive: Boolean get() = _isActiveFlow.value
    override val coroutineContext: CoroutineContext = SupervisorJob() + dispatcherProvider.io()

    override fun activate() {
        launch {
            busyIndicatorController.coWith {
                processor.activate(ActivationRequest(codeValueProvider.code)).let { r ->
                    val isActive = r?.isActive.orDefault()
                    updateIsActive(isActive)
                    if (isActive.not()) dialogToShowUpdater.updateDialogToShow(ActivationFailedDialogId)
                }
            }
        }
    }

    private fun updateIsActive(isActive: Boolean) {
        _isActiveFlow.value = isActive
    }
}