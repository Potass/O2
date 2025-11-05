package cz.jvyh.o2.scratch.shared.common.platform

import cz.jvyh.o2.scratch.shared.common.infrastructure.EMPTY_STRING
import io.kotest.matchers.shouldBe
import io.mockk.mockk
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
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class BusyIndicatorControllerImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val any: Any = mockk()
    private val anyBlock: () -> Any = { testDispatcher.scheduler.advanceUntilIdle(); any }
    private val anySuspendBlock: suspend () -> Any = suspend { anyBlock() }
    private val anyThrowingBlock: () -> Any = { testDispatcher.scheduler.advanceUntilIdle(); throw Exception() }
    private val anyThrowingSuspendBlock: suspend () -> Any = suspend { anyThrowingBlock() }

    private lateinit var controller: BusyIndicatorControllerImpl

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        controller = BusyIndicatorControllerImpl()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when show should get true from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        controller.show()
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true)
        collectJob.cancel()
    }

    @Test
    fun `when show 3 times should get true once from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        repeat(3) {
            controller.show()
            testDispatcher.scheduler.advanceUntilIdle()
        }

        collectedItems shouldBe listOf(true)
        collectJob.cancel()
    }

    @Test
    fun `given no show when hide should get false from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        controller.hide(EMPTY_STRING)
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(false)
        collectJob.cancel()
    }

    @Test
    fun `given ignored show id when hide should get just true from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        controller.show()
        testDispatcher.scheduler.advanceUntilIdle()
        controller.hide(EMPTY_STRING)
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true)
        collectJob.cancel()
    }

    @Test
    fun `given not ignored show id when hide should get true then false from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        val id = controller.show()
        testDispatcher.scheduler.advanceUntilIdle()
        controller.hide(id)
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true, false)
        collectJob.cancel()
    }

    @Test
    fun `given not ignored and ignored show ids when hide should get just true from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        val id = controller.show()
        testDispatcher.scheduler.advanceUntilIdle()
        controller.show()
        testDispatcher.scheduler.advanceUntilIdle()
        controller.hide(id)
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true)
        collectJob.cancel()
    }

    @Test
    fun `given 2 not ignored show ids when hide should get true then false from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }
        val id = controller.show()
        testDispatcher.scheduler.advanceUntilIdle()
        val id2 = controller.show()
        testDispatcher.scheduler.advanceUntilIdle()

        controller.hide(id)
        testDispatcher.scheduler.advanceUntilIdle()
        controller.hide(id2)
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true, false)
        collectJob.cancel()
    }

    @Test
    fun `given 2 not ignored show ids scenario 2 when hide should get true, false, true, false from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        val id = controller.show()
        testDispatcher.scheduler.advanceUntilIdle()
        controller.hide(id)
        testDispatcher.scheduler.advanceUntilIdle()
        val id2 = controller.show()
        testDispatcher.scheduler.advanceUntilIdle()
        controller.hide(id2)
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true, false, true, false)
        collectJob.cancel()
    }

    @Test
    fun `when with should get true then false from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        val result = controller.with(anyBlock)
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true, false)
        collectJob.cancel()
        assertEquals(any, result)
    }

    @Test
    fun `when co-with should get true then false from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }

        val result = controller.coWith(anySuspendBlock)
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true, false)
        collectJob.cancel()
        assertEquals(any, result)
    }

    @Test
    fun `given exception when with should get true then false from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }
        var wasExceptionCaught = false

        runCatching { controller.with(anyThrowingBlock) }.onFailure { wasExceptionCaught = true }
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true, false)
        collectJob.cancel()
        assertTrue(wasExceptionCaught)
    }

    @Test
    fun `given exception when co-with should get true then false from flow`() = testScope.runTest {
        val collectedItems = mutableListOf<Boolean>()
        val collectJob = launch { controller.busyIndicatorVisibilityFlow.collect { collectedItems.add(it) } }
        var wasExceptionCaught = false

        runCatching { controller.coWith(anyThrowingSuspendBlock) }.onFailure { wasExceptionCaught = true }
        testDispatcher.scheduler.advanceUntilIdle()

        collectedItems shouldBe listOf(true, false)
        collectJob.cancel()
        assertTrue(wasExceptionCaught)
    }
}