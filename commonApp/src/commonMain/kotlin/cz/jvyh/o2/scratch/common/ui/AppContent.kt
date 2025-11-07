package cz.jvyh.o2.scratch.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.domain.AppStringKey
import cz.jvyh.o2.scratch.common.domain.shared.ActivationFailedDialogId
import cz.jvyh.o2.scratch.shared.common.domain.DialogId
import cz.jvyh.o2.scratch.shared.common.ui.composables.AppAlertDialog
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppStringProvider
import cz.jvyh.o2.scratch.shared.logging.infrastructure.LOGGER_SIMPLE_NAMED_FACTORY
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Composable
fun InitAppContent(
    onAfterInit: @Composable () -> Unit,
) {
    val appStringProvider = koinInject<AppStringProvider>()
    var isLoaded by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            appStringProvider.load(false)
            isLoaded = true
        }
    }

    if (isLoaded) onAfterInit()
}

@Composable
expect fun AppContent(
    viewModel: AppContentViewModel = koinViewModel(),
    logger: Logger = koinInject<Logger>(qualifier = named(LOGGER_SIMPLE_NAMED_FACTORY)) { parametersOf("AppContent") },
)

@Composable
fun ProcessingOverlay(
    isProcessing: Boolean,
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
        if (isProcessing) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .pointerInput(Unit) { detectTapGestures {} }, // Block interaction
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun AppDialog(
    id: DialogId?,
    onDismiss: () -> Unit,
    isProcessing: Boolean,
) {
    if (isProcessing.not()) {
        when (id) {
            ActivationFailedDialogId -> AppAlertDialog(
                bodyText = AppStringKey.ActivationDialogMsgActivationFailed,
                onConfirm = onDismiss
            )
        }
    }
}