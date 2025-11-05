package cz.jvyh.o2.scratch.common.ui.main

import cz.jvyh.o2.scratch.common.domain.AppStringKey
import cz.jvyh.o2.scratch.shared.common.ui.destination.FullscreenDestination

data object MainDestination : FullscreenDestination<AppStringKey>() {
    override val title: AppStringKey = AppStringKey.MainDestinationTitle
}