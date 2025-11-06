package cz.jvyh.o2.scratch.common.ui.activation

import cz.jvyh.o2.scratch.common.domain.AppStringKey
import cz.jvyh.o2.scratch.shared.common.ui.destination.FullscreenDestination

data object ActivationDestination : FullscreenDestination<AppStringKey>() {
    override val title: AppStringKey = AppStringKey.ActivationDestinationTitle
}