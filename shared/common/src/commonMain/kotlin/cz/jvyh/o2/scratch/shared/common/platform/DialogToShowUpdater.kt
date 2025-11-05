package cz.jvyh.o2.scratch.shared.common.platform

import cz.jvyh.o2.scratch.shared.common.domain.DialogId

interface DialogToShowUpdater {
    fun updateDialogToShow(id: DialogId)
}