package cz.jvyh.o2.scratch.shared.common.ui.preview

import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.mp.KoinPlatformTools

@Composable
internal fun CommonKoinAwarePreview(
    module: Module? = null,
    composable: @Composable () -> Unit,
    builder: KoinApplication.() -> Unit = {}
) {
    if (KoinPlatformTools.defaultContext().getOrNull() == null) {
        KoinApplication(
            application = {
                builder()
                module?.let(::modules)
            }
        ) { composable() }
    } else {
        composable()
    }
}