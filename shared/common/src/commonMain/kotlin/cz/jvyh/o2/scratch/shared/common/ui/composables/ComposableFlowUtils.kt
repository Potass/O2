package cz.jvyh.o2.scratch.shared.common.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Suppress("KotlinNoActualForExpect")
@Composable
expect fun <T> StateFlow<T>.collectAsStateWithLifecycleMultiplatform(
    context: CoroutineContext = EmptyCoroutineContext,
): State<T>