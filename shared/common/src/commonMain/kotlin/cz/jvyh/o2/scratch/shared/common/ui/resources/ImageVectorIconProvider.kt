package cz.jvyh.o2.scratch.shared.common.ui.resources

import androidx.compose.ui.graphics.vector.ImageVector
import cz.jvyh.o2.scratch.shared.common.domain.IconKey

interface ImageVectorIconProvider<I : IconKey> : IconProvider<I, ImageVector>