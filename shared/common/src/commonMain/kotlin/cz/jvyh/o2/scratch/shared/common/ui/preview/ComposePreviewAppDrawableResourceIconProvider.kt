package cz.jvyh.o2.scratch.shared.common.ui.preview

import cz.jvyh.o2.scratch.shared.common.Res
import cz.jvyh.o2.scratch.shared.common.domain.DrawableResourceIconKey
import cz.jvyh.o2.scratch.shared.common.icon__preview_24dp
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppDrawableResourceIconProvider
import org.jetbrains.compose.resources.DrawableResource

internal class ComposePreviewAppDrawableResourceIconProvider : AppDrawableResourceIconProvider {
    override fun getIcon(key: DrawableResourceIconKey): DrawableResource = Res.drawable.icon__preview_24dp
}