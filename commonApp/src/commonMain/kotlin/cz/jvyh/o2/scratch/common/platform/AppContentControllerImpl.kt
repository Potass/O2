@file:Suppress("UnusedFlow")

package cz.jvyh.o2.scratch.common.platform

import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorVisibilityProvider
import cz.jvyh.o2.scratch.shared.common.platform.DialogToShowProvider

class AppContentControllerImpl(
    private val busyIndicatorVisibilityProvider: BusyIndicatorVisibilityProvider,
    private val dialogToShowProvider: DialogToShowProvider,
) : AppContentController,
    BusyIndicatorVisibilityProvider by busyIndicatorVisibilityProvider,
    DialogToShowProvider by dialogToShowProvider