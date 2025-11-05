package cz.jvyh.o2.scratch.common.ui.preview

import org.koin.dsl.module

val composePreviewAppPlatformModule = module {
    includes(
        composePreviewAppModule,
    )
}