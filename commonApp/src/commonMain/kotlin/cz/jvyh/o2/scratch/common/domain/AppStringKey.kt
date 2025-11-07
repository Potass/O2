package cz.jvyh.o2.scratch.common.domain

import cz.jvyh.o2.scratch.shared.common.domain.StringKey

sealed interface AppStringKey : StringKey {
    data object ActivationDestinationTitle : AppStringKey
    data object ActivationDialogMsgActivationFailed : AppStringKey
    data object AppName : AppStringKey
    data object MainDestinationTitle : AppStringKey
    data object ScratchDestinationTitle : AppStringKey
}