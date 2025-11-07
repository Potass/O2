package cz.jvyh.o2.scratch.common.platform.scratch

internal interface ScratchController {
    suspend fun scratch(): String
}