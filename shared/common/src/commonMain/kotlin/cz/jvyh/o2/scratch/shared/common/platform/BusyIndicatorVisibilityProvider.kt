package cz.jvyh.o2.scratch.shared.common.platform

import kotlinx.coroutines.flow.Flow

interface BusyIndicatorVisibilityProvider {
    val busyIndicatorVisibilityFlow: Flow<Boolean>
}