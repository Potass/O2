package cz.jvyh.o2.scratch.common.platform.scratch

import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import io.mockk.mockk
import kotlin.test.BeforeTest

internal class ScratchControllerImplTest {
    private val anyBusyIndicatorController: BusyIndicatorController = mockk()

    private lateinit var controller: ScratchControllerImpl

    @BeforeTest
    fun setUp() {
        controller = ScratchControllerImpl(
            busyIndicatorController = anyBusyIndicatorController
        )
    }

    // TODO O2 - unit tests
}