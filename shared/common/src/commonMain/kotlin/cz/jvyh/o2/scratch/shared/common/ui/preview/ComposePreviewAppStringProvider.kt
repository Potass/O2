package cz.jvyh.o2.scratch.shared.common.ui.preview

import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.domain.StringListKey
import cz.jvyh.o2.scratch.shared.common.infrastructure.doNothing
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppStringProvider

internal class ComposePreviewAppStringProvider : AppStringProvider {
    override suspend fun load(force: Boolean) = doNothing()

    override fun getString(key: StringKey): String = key.toString()

    override fun getString(key: StringKey, vararg args: Any): String = key.toString()

    override fun getStringList(key: StringListKey): List<String> = listOf("string1", "string2")
}