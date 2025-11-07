package cz.jvyh.o2.scratch.common.platform.activation

import cz.jvyh.o2.scratch.common.domain.activation.ActivationRequest
import cz.jvyh.o2.scratch.shared.common.infrastructure.DispatcherProvider
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

// TODO O2 - unit tests
internal class ActivationControllerImpl(
    dispatcherProvider: DispatcherProvider,
    private val processor: ActivationProcessor,
    private val busyIndicatorController: BusyIndicatorController,
) : ActivationController, CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob() + dispatcherProvider.io()

    override fun activate() {
        launch {
            busyIndicatorController.coWith {
                processor.activate(ActivationRequest("TODO_UUID"))
                // TODO O2 - handle result; pass to Flow; or show error dialog
            }
        }
    }
}