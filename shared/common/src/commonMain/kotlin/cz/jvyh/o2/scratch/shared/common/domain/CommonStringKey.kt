package cz.jvyh.o2.scratch.shared.common.domain

sealed interface CommonStringKey : StringKey {
    data object CommonLabelActivate : CommonStringKey
    data object CommonLabelOk : CommonStringKey
    data object CommonLabelScratch : CommonStringKey
    data object PreviewDestinationTitle : CommonStringKey
}