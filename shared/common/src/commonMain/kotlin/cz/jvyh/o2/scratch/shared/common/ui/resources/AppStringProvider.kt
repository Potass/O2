package cz.jvyh.o2.scratch.shared.common.ui.resources

import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.domain.StringListKey

interface AppStringProvider {
    suspend fun load(force: Boolean)
    fun getString(key: StringKey): String
    fun getString(key: StringKey, vararg args: Any): String
    fun getStringList(key: StringListKey): List<String>
}