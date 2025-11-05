package cz.jvyh.o2.scratch.shared.common.ui.resources

import cz.jvyh.o2.scratch.shared.common.domain.IconKey
import org.jetbrains.compose.resources.DrawableResource

interface DrawableResourceIconProvider<I : IconKey> : IconProvider<I, DrawableResource>