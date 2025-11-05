package cz.jvyh.o2.scratch.shared.networking.di

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.logging.Logger

actual val httpNetworkEngineFactory: io.ktor.client.engine.HttpClientEngineFactory<*>
    get() = TODO("Not yet implemented")
actual val networkLogger: Logger
    get() = TODO("Not yet implemented")