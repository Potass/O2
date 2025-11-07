@file:OptIn(ExperimentalUuidApi::class)

package cz.jvyh.o2.scratch.common.platform.main

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal class MainControllerImpl : MainController {
    private fun createUUID() = Uuid.random().toHexString()
}