@file:OptIn(ExperimentalUuidApi::class)

package cz.jvyh.o2.scratch.common.platform.scratch

import cz.jvyh.o2.scratch.common.platform.shared.CodeFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider
import cz.jvyh.o2.scratch.shared.common.infrastructure.StringDefaults
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal class ScratchControllerImpl(
    private val busyIndicatorController: BusyIndicatorController,
) : ScratchController, CodeFlowProvider, CodeValueProvider {
    private val _codeFlow = MutableStateFlow(StringDefaults.DEFAULT_VALUE)
    override val codeFlow: Flow<String> = _codeFlow
    override val code: String get() = _codeFlow.value

    override suspend fun scratch(): String = busyIndicatorController.coWith {
        delay(REQUESTED_DELAY)
        createUUID().also(::updateCode)
    }

    private fun createUUID() = Uuid.random().toHexString()

    private fun updateCode(code: String) {
        _codeFlow.value = code
    }

    private companion object {
        const val REQUESTED_DELAY = 2_000L
    }
}