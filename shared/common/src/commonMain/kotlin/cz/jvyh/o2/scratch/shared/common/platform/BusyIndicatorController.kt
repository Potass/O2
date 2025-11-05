package cz.jvyh.o2.scratch.shared.common.platform

interface BusyIndicatorController {
    fun <R> with(block: () -> R): R
    suspend fun <R> coWith(block: suspend () -> R): R
    fun show(): String
    fun hide(id: String)
}