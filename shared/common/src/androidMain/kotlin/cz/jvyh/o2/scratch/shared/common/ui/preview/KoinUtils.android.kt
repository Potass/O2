package cz.jvyh.o2.scratch.shared.common.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module

@Composable
fun KoinAwarePreview(
    module: Module?,
    composable: @Composable () -> Unit
) {
    val context = LocalContext.current

    CommonKoinAwarePreview(
        module = module,
        composable = composable,
        builder = { androidContext(context) }
    )
}