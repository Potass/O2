package cz.jvyh.o2.scratch.shared.common.ui.resources

import cz.jvyh.o2.scratch.shared.common.domain.IconKey

interface IconProvider<I : IconKey, ReturnType : Any> {
    fun getIcon(key: I): ReturnType
}