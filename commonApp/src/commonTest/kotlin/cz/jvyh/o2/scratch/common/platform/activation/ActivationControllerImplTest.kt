@file:OptIn(ExperimentalCoroutinesApi::class)

package cz.jvyh.o2.scratch.common.platform.activation

import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider
import cz.jvyh.o2.scratch.shared.common.infrastructure.DispatcherProvider
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import cz.jvyh.o2.scratch.shared.common.platform.DialogToShowUpdater
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

internal class ActivationControllerImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val anyDispatcherProvider: DispatcherProvider = mockk()
    private val anyProcessor: ActivationProcessor = mockk()
    private val anyBusyIndicatorController: BusyIndicatorController = mockk()
    private val anyCodeValueProvider: CodeValueProvider = mockk()
    private val anyDialogToShowUpdater: DialogToShowUpdater = mockk()

    private lateinit var controller: ActivationControllerImpl

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { anyDispatcherProvider.io() } returns testDispatcher

        controller = ActivationControllerImpl(
            dispatcherProvider = anyDispatcherProvider,
            processor = anyProcessor,
            busyIndicatorController = anyBusyIndicatorController,
            codeValueProvider = anyCodeValueProvider,
            dialogToShowUpdater = anyDialogToShowUpdater
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // TODO O2 - unit tests
}