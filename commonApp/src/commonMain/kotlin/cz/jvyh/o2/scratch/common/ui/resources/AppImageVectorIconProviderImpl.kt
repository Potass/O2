package cz.jvyh.o2.scratch.common.ui.resources

import androidx.compose.ui.graphics.vector.ImageVector
import cz.jvyh.o2.scratch.shared.common.domain.CommonImageVectorIconKey
import cz.jvyh.o2.scratch.shared.common.domain.ImageVectorIconKey
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppImageVectorIconProvider
import cz.jvyh.o2.scratch.shared.common.ui.resources.CommonImageVectorIconProvider

class AppImageVectorIconProviderImpl(
    private val commonImageVectorIconProvider: CommonImageVectorIconProvider
) : AppImageVectorIconProvider {
    override fun getIcon(key: ImageVectorIconKey): ImageVector = when (key) {
        is CommonImageVectorIconKey -> commonImageVectorIconProvider.getIcon(key)
        else -> throw IllegalArgumentException("Unknown icon key: $key")
    }
}