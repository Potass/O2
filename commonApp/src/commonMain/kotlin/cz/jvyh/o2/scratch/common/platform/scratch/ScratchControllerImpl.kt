@file:OptIn(ExperimentalUuidApi::class)

package cz.jvyh.o2.scratch.common.platform.scratch

import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import cz.jvyh.o2.scratch.shared.logging.infrastructure.LOGGER_CLASS_NAMED_FACTORY
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

// TODO O2 - unit tests
internal class ScratchControllerImpl(
    private val busyIndicatorController: BusyIndicatorController,
) : ScratchController, KoinComponent {
    // TODO O2 - delete later
    private val logger: Logger by inject(qualifier = named(LOGGER_CLASS_NAMED_FACTORY)) { parametersOf(this) }

    override suspend fun scratch(): String = busyIndicatorController.coWith {
        delay(2_000L) // Simulate requested delay.
        createUUID()
    }

    private fun createUUID() = Uuid.random().toHexString()
}