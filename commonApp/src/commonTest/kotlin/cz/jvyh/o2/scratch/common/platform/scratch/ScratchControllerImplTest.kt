package cz.jvyh.o2.scratch.common.platform.scratch

import cz.jvyh.o2.scratch.shared.common.infrastructure.StringDefaults
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.CapturingSlot
import io.mockk.coEvery
import io.mockk.coInvoke
import io.mockk.mockk
import io.mockk.slot
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
internal class ScratchControllerImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val anyBusyIndicatorController: BusyIndicatorController = mockk()

    private lateinit var controller: ScratchControllerImpl

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        controller = ScratchControllerImpl(
            busyIndicatorController = anyBusyIndicatorController
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given initialized when code flow should get default value`() = testScope.runTest {
        val collectedItems = mutableListOf<String>()
        val collectJob = launch { controller.codeFlow.collect { collectedItems.add(it) } }

        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(StringDefaults.DEFAULT_VALUE)
        controller.code shouldBe StringDefaults.DEFAULT_VALUE
        collectJob.cancel()
    }

    @Test
    fun `when scratch should update code flow and get code`() = testScope.runTest {
        val collectedItems = mutableListOf<String>()
        val collectJob = launch { controller.codeFlow.collect { collectedItems.add(it) } }
        testDispatcher.scheduler.advanceUntilIdle()
        val blockCaptor: CapturingSlot<suspend () -> String> = slot()
        coEvery { anyBusyIndicatorController.coWith(capture(blockCaptor)) } coAnswers { blockCaptor.coInvoke() }

        val code = controller.scratch()
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems.should {
            it.size shouldBe 2
            it[1].length shouldBe 32
            it[1] shouldBe code
        }
        controller.code shouldBe code
        collectJob.cancel()
    }
}