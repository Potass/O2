package cz.jvyh.o2.scratch.common.platform.activation

import cz.jvyh.o2.scratch.common.domain.activation.ActivationResponse
import cz.jvyh.o2.scratch.common.domain.shared.ActivationFailedDialogId
import cz.jvyh.o2.scratch.common.platform.shared.CodeValueProvider
import cz.jvyh.o2.scratch.shared.common.infrastructure.BooleanDefaults
import cz.jvyh.o2.scratch.shared.common.infrastructure.DispatcherProvider
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import cz.jvyh.o2.scratch.shared.common.platform.DialogToShowUpdater
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.CapturingSlot
import io.mockk.coEvery
import io.mockk.coInvoke
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
@Suppress("KotlinConstantConditions")
internal class ActivationControllerImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val anyDispatcherProvider: DispatcherProvider = mockk()
    private val anyProcessor: ActivationProcessor = mockk()
    private val anyBusyIndicatorController: BusyIndicatorController = mockk()
    private val anyCodeValueProvider: CodeValueProvider = mockk()
    private val anyDialogToShowUpdater: DialogToShowUpdater = mockk()

    private val anyCode = "anyCode"
    private val anyActivationResponse: ActivationResponse = mockk()

    private lateinit var controller: ActivationControllerImpl

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { anyDispatcherProvider.io() } returns testDispatcher
        every { anyCodeValueProvider.code } returns anyCode

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

    @Test
    fun `given initialized when is active flow should get default value`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.isActiveFlow.collect { collectedItems.add(it) } }

        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(BooleanDefaults.DEFAULT_VALUE)
        controller.isActive shouldBe BooleanDefaults.DEFAULT_VALUE
        collectJob.cancel()
    }

    @Test
    fun `given happy day scenario when activate should update is activate flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.isActiveFlow.collect { collectedItems.add(it) } }
        testDispatcher.scheduler.advanceUntilIdle()
        val blockCaptor: CapturingSlot<suspend () -> Boolean> = slot()
        coEvery { anyBusyIndicatorController.coWith(capture(blockCaptor)) } coAnswers { blockCaptor.coInvoke() }
        every { anyActivationResponse.isActive } returns true
        coEvery { anyProcessor.activate(any()) } returns anyActivationResponse

        controller.activate()
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems.should {
            it.size shouldBe 2
            it[1] shouldBe true
        }
        controller.isActive shouldBe true
        verify(exactly = 0) { anyDialogToShowUpdater.updateDialogToShow(any()) }
        collectJob.cancel()
    }

    @Test
    fun `given false result when activate should leave activate flow as is`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.isActiveFlow.collect { collectedItems.add(it) } }
        testDispatcher.scheduler.advanceUntilIdle()
        val blockCaptor: CapturingSlot<suspend () -> Boolean> = slot()
        coEvery { anyBusyIndicatorController.coWith(capture(blockCaptor)) } coAnswers { blockCaptor.coInvoke() }
        every { anyActivationResponse.isActive } returns false
        coEvery { anyProcessor.activate(any()) } returns anyActivationResponse
        every { anyDialogToShowUpdater.updateDialogToShow(ActivationFailedDialogId) } returns Unit

        controller.activate()
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(BooleanDefaults.DEFAULT_VALUE)
        controller.isActive shouldBe false
        verify(exactly = 1) { anyDialogToShowUpdater.updateDialogToShow(ActivationFailedDialogId) }
        collectJob.cancel()
    }

    @Test
    fun `given null result when activate should leave activate flow as is`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.isActiveFlow.collect { collectedItems.add(it) } }
        testDispatcher.scheduler.advanceUntilIdle()
        val blockCaptor: CapturingSlot<suspend () -> Boolean> = slot()
        coEvery { anyBusyIndicatorController.coWith(capture(blockCaptor)) } coAnswers { blockCaptor.coInvoke() }
        coEvery { anyProcessor.activate(any()) } returns null
        every { anyDialogToShowUpdater.updateDialogToShow(ActivationFailedDialogId) } returns Unit

        controller.activate()
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(BooleanDefaults.DEFAULT_VALUE)
        controller.isActive shouldBe false
        verify(exactly = 1) { anyDialogToShowUpdater.updateDialogToShow(ActivationFailedDialogId) }
        collectJob.cancel()
    }
}