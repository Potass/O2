package cz.jvyh.o2.scratch.shared.common.ui.destination

import cz.jvyh.o2.scratch.shared.common.domain.StringKey

sealed interface AppDestination<S : StringKey> {
    val title: S
    val route: String get() = this::class.simpleName!!
}