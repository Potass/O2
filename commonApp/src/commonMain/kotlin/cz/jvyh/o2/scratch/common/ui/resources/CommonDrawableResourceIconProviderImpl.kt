package cz.jvyh.o2.scratch.common.ui.resources

import cz.jvyh.o2.scratch.shared.common.domain.CommonDrawableResourceIconKey
import cz.jvyh.o2.scratch.shared.common.ui.resources.CommonDrawableResourceIconProvider
import org.jetbrains.compose.resources.DrawableResource

class CommonDrawableResourceIconProviderImpl : CommonDrawableResourceIconProvider {
    override fun getIcon(key: CommonDrawableResourceIconKey): DrawableResource = throw IllegalArgumentException("Invalid key: $key")
}