package cz.jvyh.o2.scratch.common.ui.scratch

import cz.jvyh.o2.scratch.common.domain.AppStringKey
import cz.jvyh.o2.scratch.shared.common.ui.destination.FullscreenDestination

data object ScratchDestination : FullscreenDestination<AppStringKey>() {
    override val title: AppStringKey = AppStringKey.ScratchDestinationTitle
}