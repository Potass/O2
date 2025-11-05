package cz.jvyh.o2.scratch.common.ui.resources

import cz.jvyh.o2.scratch.shared.common.domain.CommonDrawableResourceIconKey
import cz.jvyh.o2.scratch.shared.common.domain.DrawableResourceIconKey
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppDrawableResourceIconProvider
import cz.jvyh.o2.scratch.shared.common.ui.resources.CommonDrawableResourceIconProvider
import org.jetbrains.compose.resources.DrawableResource

class AppDrawableResourceIconProviderImpl(
    private val commonDrawableResourceIconProvider: CommonDrawableResourceIconProvider
) : AppDrawableResourceIconProvider {
    override fun getIcon(key: DrawableResourceIconKey): DrawableResource = when (key) {
        is CommonDrawableResourceIconKey -> commonDrawableResourceIconProvider.getIcon(key)
        else -> throw IllegalArgumentException("Unknown icon key: $key")
    }
}