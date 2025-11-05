@file:OptIn(ExperimentalUuidApi::class)

package cz.jvyh.o2.scratch.shared.common.platform

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class BusyIndicatorControllerImpl : BusyIndicatorController, BusyIndicatorVisibilityProvider, BusyIndicatorInvalidator {
    private val uuidCache = mutableListOf<String>()
    private val _busyIndicatorVisibilityFlow = MutableStateFlow(false)
    override val busyIndicatorVisibilityFlow: Flow<Boolean> = _busyIndicatorVisibilityFlow

    override fun <R> with(block: () -> R): R {
        val id = show()
        return try {
            block()
        } finally {
            hide(id)
        }
    }

    override suspend fun <R> coWith(block: suspend () -> R): R {
        val id = show()
        return try {
            block()
        } finally {
            hide(id)
        }
    }

    override fun show(): String {
        val uuid = createUUID()
        uuidCache.add(uuid)
        _busyIndicatorVisibilityFlow.value = true
        return uuid
    }

    override fun hide(id: String) {
        uuidCache.remove(id)
        if (uuidCache.isEmpty()) _busyIndicatorVisibilityFlow.value = false
    }

    override fun invalidate() {
        uuidCache.clear()
        _busyIndicatorVisibilityFlow.value = false
    }

    private fun createUUID() = Uuid.random().toHexString()
}