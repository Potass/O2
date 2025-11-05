package cz.jvyh.o2.scratch.shared.common.ui.preview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Preview
import androidx.compose.ui.graphics.vector.ImageVector
import cz.jvyh.o2.scratch.shared.common.domain.ImageVectorIconKey
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppImageVectorIconProvider

internal class ComposePreviewAppImageVectorIconProvider : AppImageVectorIconProvider {
    override fun getIcon(key: ImageVectorIconKey): ImageVector = Icons.Default.Preview
}