@file:OptIn(ExperimentalUuidApi::class)

package cz.jvyh.o2.scratch.common.platform.scratch

import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.platform.shared.CodeFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.CodeInvalidator
import cz.jvyh.o2.scratch.common.platform.shared.CodeUpdater
import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider
import cz.jvyh.o2.scratch.shared.common.infrastructure.StringDefaults
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import cz.jvyh.o2.scratch.shared.logging.infrastructure.LOGGER_CLASS_NAMED_FACTORY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

// TODO O2 - unit tests
internal class ScratchControllerImpl(
    private val busyIndicatorController: BusyIndicatorController,
) : ScratchController, KoinComponent, CodeUpdater, CodeInvalidator, CodeFlowProvider, CodeValueProvider {
    private val _codeFlow = MutableStateFlow(StringDefaults.DEFAULT_VALUE)
    override val codeFlow: Flow<String> = _codeFlow
    override val code: String get() = _codeFlow.value

    // TODO O2 - delete later
    private val logger: Logger by inject(qualifier = named(LOGGER_CLASS_NAMED_FACTORY)) { parametersOf(this) }

    override suspend fun scratch(): String = busyIndicatorController.coWith {
        delay(2_000L) // Simulate requested delay.
        createUUID().also(::updateCode)
    }

    private fun createUUID() = Uuid.random().toHexString()

    override fun invalidateCode() = updateCode(StringDefaults.DEFAULT_VALUE)

    override fun updateCode(code: String) {
        _codeFlow.value = code
    }
}