package cz.jvyh.o2.scratch.common.platform.main

import cz.jvyh.o2.scratch.common.platform.shared.CodeFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider
import cz.jvyh.o2.scratch.common.platform.shared.IsActiveFlowProvider
import cz.jvyh.o2.scratch.common.platform.shared.IsActiveValueProvider
import cz.jvyh.o2.scratch.shared.common.infrastructure.EMPTY_STRING
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlin.test.BeforeTest
import kotlin.test.Test

@Suppress("UnusedFlow")
internal class MainControllerImplTest {
    private val anyIsActiveFlowProvider: IsActiveFlowProvider = mockk()
    private val anyIsActiveValueProvider: IsActiveValueProvider = mockk()
    private val anyCodeFlowProvider: CodeFlowProvider = mockk()
    private val anyCodeValueProvider: CodeValueProvider = mockk()

    private lateinit var controller: MainControllerImpl

    @BeforeTest
    fun setUp() {
        controller = MainControllerImpl(
            isActiveFlowProvider = anyIsActiveFlowProvider,
            isActiveValueProvider = anyIsActiveValueProvider,
            codeFlowProvider = anyCodeFlowProvider,
            codeValueProvider = anyCodeValueProvider
        )
    }

    @Test
    fun `when is active flow should delegate`() {
        every { anyIsActiveFlowProvider.isActiveFlow } returns flowOf()

        controller.isActiveFlow

        verify(exactly = 1) { anyIsActiveFlowProvider.isActiveFlow }
    }

    @Test
    fun `when is active value should delegate`() {
        every { anyIsActiveValueProvider.isActive } returns true

        controller.isActive

        verify(exactly = 1) { anyIsActiveValueProvider.isActive }
    }

    @Test
    fun `when code flow should delegate`() {
        every { anyCodeFlowProvider.codeFlow } returns flowOf()

        controller.codeFlow

        verify(exactly = 1) { anyCodeFlowProvider.codeFlow }
    }

    @Test
    fun `when code value should delegate`() {
        every { anyCodeValueProvider.code } returns EMPTY_STRING

        controller.code

        verify(exactly = 1) { anyCodeValueProvider.code }
    }
}