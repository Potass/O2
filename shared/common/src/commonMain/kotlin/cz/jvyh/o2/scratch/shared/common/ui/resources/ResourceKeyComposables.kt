package cz.jvyh.o2.scratch.shared.common.ui.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import cz.jvyh.o2.scratch.shared.common.domain.DrawableResourceIconKey
import cz.jvyh.o2.scratch.shared.common.domain.IconKey
import cz.jvyh.o2.scratch.shared.common.domain.ImageVectorIconKey
import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.domain.StringListKey
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject

@Composable
fun stringKeyResource(key: StringKey): String {
    val appStringProvider = koinInject<AppStringProvider>()
    return appStringProvider.getString(key)
}

@Composable
fun stringKeyResource(key: StringKey, vararg args: Any): String {
    val appStringProvider = koinInject<AppStringProvider>()
    return appStringProvider.getString(key, *args)
}

@Composable
fun stringListKeyResource(key: StringListKey): List<String> {
    val appStringProvider = koinInject<AppStringProvider>()
    return appStringProvider.getStringList(key)
}

@Composable
fun iconKeyResource(key: IconKey): ImageVector = when (key) {
    is ImageVectorIconKey -> iconKeyResource(key)
    is DrawableResourceIconKey -> iconKeyResource(key)
    else -> throw IllegalArgumentException("Unknown icon key: $key")
}

@Composable
fun iconKeyResource(key: ImageVectorIconKey): ImageVector {
    val appImageVectorIconProvider = koinInject<AppImageVectorIconProvider>()
    return appImageVectorIconProvider.getIcon(key)
}

@Composable
fun iconKeyResource(key: DrawableResourceIconKey): ImageVector {
    val appDrawableResourceIconProvider = koinInject<AppDrawableResourceIconProvider>()
    return vectorResource(appDrawableResourceIconProvider.getIcon(key))
}