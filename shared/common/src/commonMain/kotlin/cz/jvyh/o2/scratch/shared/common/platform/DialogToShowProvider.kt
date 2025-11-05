package cz.jvyh.o2.scratch.shared.common.platform

import cz.jvyh.o2.scratch.shared.common.domain.DialogId
import kotlinx.coroutines.flow.Flow

interface DialogToShowProvider {
    val dialogToShowFlow: Flow<DialogId>
}