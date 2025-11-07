package cz.jvyh.o2.scratch.common.platform.shared

import kotlinx.coroutines.flow.Flow

interface IsActiveFlowProvider {
    val isActiveFlow: Flow<Boolean>
}