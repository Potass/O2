package cz.jvyh.o2.scratch.common.di

import org.koin.dsl.module

actual val appPlatformModule = module {
    includes(
        appModule
    )
}