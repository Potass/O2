package cz.jvyh.o2.scratch.shared.common.ui.preview

import cz.jvyh.o2.scratch.shared.common.domain.CommonStringKey
import cz.jvyh.o2.scratch.shared.common.ui.destination.FullscreenDestination

data class ComposePreviewDestination(
    override val title: CommonStringKey = CommonStringKey.PreviewDestinationTitle,
) : FullscreenDestination<CommonStringKey>()