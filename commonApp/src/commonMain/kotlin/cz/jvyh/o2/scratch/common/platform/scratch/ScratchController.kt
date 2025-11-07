package cz.jvyh.o2.scratch.common.platform.scratch

import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider

internal interface ScratchController : CodeValueProvider {
    suspend fun scratch(): String
}