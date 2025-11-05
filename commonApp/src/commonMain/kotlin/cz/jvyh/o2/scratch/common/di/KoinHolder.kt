package cz.jvyh.o2.scratch.common.di

import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin

object KoinHolder {
    // Can be useful later (e.g. to unload some module/scope).
    private lateinit var koinApplication: KoinApplication

    fun create(builder: KoinApplication.() -> Unit = {}) {
        koinApplication = startKoin {
            builder()
            modules(appPlatformModule)
        }
    }
}