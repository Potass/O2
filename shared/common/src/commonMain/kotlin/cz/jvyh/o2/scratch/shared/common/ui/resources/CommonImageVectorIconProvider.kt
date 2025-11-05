package cz.jvyh.o2.scratch.shared.common.ui.resources

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import cz.jvyh.o2.scratch.shared.common.domain.CommonImageVectorIconKey

class CommonImageVectorIconProvider : ImageVectorIconProvider<CommonImageVectorIconKey> {
    override fun getIcon(key: CommonImageVectorIconKey): ImageVector = when (key) {
        CommonImageVectorIconKey.AutoMirroredFilledArrowBackImageVectorIcon -> Icons.AutoMirrored.Filled.ArrowBack
    }
}