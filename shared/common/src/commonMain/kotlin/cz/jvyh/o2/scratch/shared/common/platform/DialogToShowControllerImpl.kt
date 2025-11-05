package cz.jvyh.o2.scratch.shared.common.platform

import cz.jvyh.o2.scratch.shared.common.domain.DialogId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class DialogToShowControllerImpl : DialogToShowProvider, DialogToShowUpdater {
    private val _dialogToShowFlow = MutableSharedFlow<DialogId>(extraBufferCapacity = 1)
    override val dialogToShowFlow: Flow<DialogId> = _dialogToShowFlow

    override fun updateDialogToShow(id: DialogId) {
        _dialogToShowFlow.tryEmit(id)
    }
}